package com.frod.core.client.naver.base;

import com.frod.core.client.naver.constant.NaverHttpHeaders;
import com.frod.core.configuration.WebClientConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class NaverWebClient extends WebClientConfiguration {

    private static String END_POINT;
    private static String CLIENT_ID;
    private static String CLIENT_SECRET;

    public final HttpServletRequest request;

    public NaverWebClient(HttpServletRequest request) {
        this.request = request;
    }
    @Value("${apis.naver-search.endpoint}")
    public void setEndPoint(String endPoint) {
        END_POINT = endPoint;
    }

    @Value("${apis.naver-search.client-id}")
    public void setClientId(String clientId) {
        CLIENT_ID = clientId;
    }

    @Value("${apis.naver-search.client-secret}")
    public void setClientSecret(String clientSecret) {
        CLIENT_SECRET = clientSecret;
    }

    public WebClient client() {
        return webClient()
                .mutate()
                .baseUrl(END_POINT)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.3");
                    httpHeaders.set(NaverHttpHeaders.NAVER_CLIENT_ID.getHeaderName(),CLIENT_ID);
                    httpHeaders.set(NaverHttpHeaders.NAVER_CLIENT_SECRET.getHeaderName(),CLIENT_SECRET);
                })
                .build();
    }

    public <T> Mono<T> exchanged(WebClient.RequestBodySpec client,
                                 ParameterizedTypeReference<T> typeReference) {
        return client
                .retrieve()
                // 4xx and 5xx 처리
                .onStatus(
                        status -> status.is4xxClientError(),
                        response -> Mono.error(new RuntimeException("4xx error Exception ::::>>> "+response.statusCode().getReasonPhrase()))
                )
                .onStatus(
                        status -> status.is5xxServerError(),
                        response -> Mono.error(new RuntimeException("5xx error Exception ::::>>> "+response.statusCode().getReasonPhrase()))
                )
                .bodyToMono(typeReference);
    }

}
