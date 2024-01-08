package com.example.apigatewayservice.filter;

import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {

    public GlobalFilter() {
        super(Config.class);
    }

    @Data
    public static class Config {
        //Configuration 정보
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

    @Override
    public GatewayFilter apply(Config config) {
        //Custom PRE Filter
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Global Filter Base Message : {}", config.getBaseMessage());


            if (config.isPreLogger()) {
                log.info("Global Filter Start : Request Id -> {}", request.getId());
            }


            //Custom POST Filter
            return chain.filter(exchange).then(Mono.fromRunnable(() -> { //Mono : WebFlux 비동기 방식 서버 단일값 전달
                        if (config.isPostLogger()) {
                            log.info("Global Filter End : Response Code -> {}", request.getId());
                        }
            })
            );


        });
    }


}
