package com.frod.core.client.kakao.base;

import com.frod.core.common.exception.ExternalApiException;
import com.frod.core.configuration.WebClientConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class KakaoWebClient extends WebClientConfiguration {
    private static String END_POINT;
    private static String API_KEY;
    public final HttpServletRequest request;

    public KakaoWebClient(HttpServletRequest request) {
        this.request = request;
    }

    @Value("${apis.kakao-search.endpoint}")
    public void setEndPoint(String endPoint) {
        END_POINT = endPoint;
    }
    @Value("${apis.kakao-search.apikey}")
    public void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }

    public WebClient client() {
        return webClient()
                .mutate()
                .baseUrl(END_POINT)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.3");
                    httpHeaders.set(HttpHeaders.AUTHORIZATION, "KakaoAK "+API_KEY);
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
                        response -> Mono.error(new ExternalApiException(response.statusCode().getReasonPhrase(), response.rawStatusCode(),response.statusCode()))
                )
                .onStatus(
                        status -> status.is5xxServerError(),
                        response -> Mono.error(new ExternalApiException(response.statusCode().getReasonPhrase(), response.rawStatusCode(),response.statusCode()))
                )
                .bodyToMono(typeReference)
                .onErrorResume(WebClientResponseException.class,
                        ex -> ex.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(ex));
    }
}
