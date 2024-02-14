package com.example.appservice.config;

import io.netty.channel.ChannelOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    HttpClient httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000); // 10초


    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .defaultCookie("Default-Cookie", "value")
                .defaultHeader("Default-Header", "header")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    public ConnectionProvider connectionProvider() {
        return ConnectionProvider.builder("http")
                .maxConnections(50) // Connection Pool 개수
                .pendingAcquireTimeout(Duration.ofMillis(0)) // Connection 을 얻기 위해 기다리는 최대 시간
                .pendingAcquireMaxCount(-1) // Connection을 가져오는 시도 횟수, -1은 제한 없
                .maxIdleTime(Duration.ofMillis(1000L)) // idle 상태의 커넥션을 유지하는 시간
                .build();

    }
}
