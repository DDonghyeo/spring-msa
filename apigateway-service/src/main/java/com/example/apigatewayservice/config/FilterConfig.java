package com.example.apigatewayservice.config;

import com.example.apigatewayservice.filter.CustomFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@Configuration
public class FilterConfig {

//    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {

        CustomFilter customFilter = new CustomFilter();

        return builder.routes()
                .route(r -> r.path("/user/**") //Path 확인
                        .filters(f -> f.filter(customFilter.apply(new CustomFilter.Config()))
                        ) //필터 적용
                        .uri("lb://USER-SERVICE") //uri로 이동
                )
                .route(r -> r.path("/app/**")
                        .filters(f -> f.addRequestHeader("App Request Header", "App Service Request")
                                .addResponseHeader("App Response Header", "App Service Response")
                        ) //필터 적용
                        .uri("lb://APP-SERVICE")
                )
                .build();

    }
}
