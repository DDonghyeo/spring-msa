package com.example.appservice.service;

import com.example.appservice.dto.AppRequestDto;
import com.example.appservice.dto.UserResponseDto;
import com.example.appservice.entity.App;
import com.example.appservice.repository.AppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class AppService {

    private final AppRepository appRepository;

    private final WebClient webClient;


    @Transactional
    public void createApp(AppRequestDto appRequestDto) {
        App app = appRequestDto.toEntity();

        //단순 create 이용하기 + 에러 처리
        Mono<UserResponseDto> userResponseDtoMono =
                WebClient.create("http://localhost:9000/user/" + appRequestDto.getUserId())
                    .get()
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.createException())
                    .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> clientResponse.createException())
                    .bodyToMono(UserResponseDto.class);리

        userResponseDtoMono.subscribe(userResponseDto -> app.setUserName(userResponseDto.getName()));


        appRepository.save(app);
    }

    public void webClientExamples(AppRequestDto appRequestDto) {

        App app = appRequestDto.toEntity();

        //단순 create 이용하기 - POST 요청 시
        Mono<UserResponseDto> userResponseDtoMono1 =
                WebClient.create("http://localhost:9000/user/" + appRequestDto.getUserId())
                        .post()// POST METHOD
                        .body(Mono.just(appRequestDto), AppRequestDto.class)
                        .retrieve()
//               .bodyToMono(Void.class) // Response 내용 없을 시
                        .bodyToMono(UserResponseDto.class);

        userResponseDtoMono1.subscribe(userResponseDto -> app.setUserName(userResponseDto.getName()));


        //builder 사용하기
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:9000/user/" + appRequestDto.getUserId())
                .build();

        Mono<UserResponseDto> userResponseDtoMono2 =
                client.get()// GET METHOD
                        .retrieve().bodyToMono(UserResponseDto.class);

        userResponseDtoMono2.subscribe(userResponseDto -> app.setUserName(userResponseDto.getName()));

        //exchange 사용하기
        Mono<UserResponseDto> userResponseDtoMono3 =
                WebClient.create("http://localhost:9000/user/" + appRequestDto.getUserId())
                        .post()// POST METHOD
                        .body(Mono.just(appRequestDto), AppRequestDto.class)
                        .exchangeToMono(clientResponse -> {
                            if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                                return clientResponse.bodyToMono(UserResponseDto.class);
                            } else if (clientResponse.statusCode().is4xxClientError()) {
                                throw new RuntimeException();
                            } else if (clientResponse.statusCode().is5xxServerError()) {
                                throw new RuntimeException();
                            } else throw new RuntimeException();

                        });
    }
}
