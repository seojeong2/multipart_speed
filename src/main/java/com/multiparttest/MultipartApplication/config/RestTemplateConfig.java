package com.multiparttest.MultipartApplication.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
public class RestTemplateConfig {

    private static final int CONNECTION_TIMEOUT = 2000;
    private static final int READ_TIMEOUT = 5000;
    private static final int MAX_CONN_TOTAL = 5000;
    private static final int MAX_CONN_PER_ROUTE = 20;

    @Bean(name="restTemplate")
    public RestTemplate restTemplate(){
        //httpComponents 사용하여 pool 설정
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(CONNECTION_TIMEOUT);
        httpComponentsClientHttpRequestFactory.setReadTimeout(READ_TIMEOUT);

        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(MAX_CONN_TOTAL)
                .setMaxConnPerRoute(MAX_CONN_PER_ROUTE)
                .build();

        httpComponentsClientHttpRequestFactory.setHttpClient(httpClient);

        // logging interceptor, error handler 추가
        RestTemplate restTemplate = new RestTemplate(
                new BufferingClientHttpRequestFactory(httpComponentsClientHttpRequestFactory)
        );

        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        return restTemplate;
    }


}
