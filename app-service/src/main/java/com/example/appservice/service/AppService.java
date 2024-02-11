package com.example.appservice.service;

import com.example.appservice.dto.AppRequestDto;
import com.example.appservice.entity.App;
import com.example.appservice.repository.AppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class AppService {

    private final AppRepository appRepository;

    @Transactional
    public void createApp(AppRequestDto appRequestDto, String id) {
        App app = appRequestDto.toEntity();

        WebClient.create("http://localhost:9000/user/" + id);
        app.setUserName("username");

        appRepository.save(app);
    }
}
