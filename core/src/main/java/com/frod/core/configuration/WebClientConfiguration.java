package com.frod.core.configuration;

import com.frod.core.common.exception.ThrowingConsumer;
import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.LoggingCodecSupport;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfiguration {

    private Logger log = LoggerFactory.getLogger(WebClientConfiguration.class);

    public WebClient webClient() {

        // exchangeStrategies setting
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs())
                .build();

        // logging setting
        exchangeStrategies
                .messageWriters().stream()
                .filter(LoggingCodecSupport.class::isInstance)
                .forEach(writer -> ((LoggingCodecSupport) writer).setEnableLoggingRequestDetails(true));

        return WebClient.builder()
                .clientConnector(
                        // HTTPS 인증서를 검증하지 않고 바로 접속하는 설정과, TCP 연결 시 ConnectionTimeOut , ReadTimeOut , WriteTimeOut 을 적용하는 설정을 추가
                        new ReactorClientHttpConnector(
                                HttpClient
                                        .create()
                                        .secure(
                                                ThrowingConsumer.unchecked(
                                                        sslContextSpec -> sslContextSpec.sslContext(
                                                                SslContextBuilder.forClient()
                                                                        .trustManager(
                                                                                InsecureTrustManagerFactory.INSTANCE)
                                                                        .build()
                                                        )
                                                )
                                        )
                                        // connection time 설정
                                        .option(
                                                ChannelOption.CONNECT_TIMEOUT_MILLIS,
                                                120_000
                                        )
                                        .doOnConnected(conn -> conn.addHandlerLast(
                                                                new ReadTimeoutHandler(30)
                                                        )
                                                        .addHandlerLast(
                                                                new WriteTimeoutHandler(15)
                                                        )
                                        )
                        )
                )
                .exchangeStrategies(exchangeStrategies)
                .filter(ExchangeFilterFunction.ofRequestProcessor(
                        clientRequest -> {
                            log.info("Request: {} {} {}", clientRequest.method(), clientRequest.url(), clientRequest.headers());
                            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.debug("{} : {}", name, value)));
                            return Mono.just(clientRequest);
                        }
                ))
                .filter(ExchangeFilterFunction.ofResponseProcessor(
                        clientResponse -> {
                            log.info("Response: {} {}", clientResponse.statusCode().getReasonPhrase(), clientResponse.logPrefix());
                            clientResponse.headers().asHttpHeaders().forEach((name, values) -> values.forEach(value -> log.debug("{} : {}", name, value)));
                            return Mono.just(clientResponse);
                        }
                ))
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.3");
                })
                //Response Body size 가 클경우 기본 256kb로 커버가 안되어 10mb로 변경
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024))
                .build();
    }

}
