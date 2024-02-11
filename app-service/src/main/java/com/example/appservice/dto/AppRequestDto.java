package com.example.appservice.dto;

import com.example.appservice.entity.App;
import lombok.Getter;

@Getter
public class AppRequestDto {

    public String name;

    public String type;

    public String userId;

    public App toEntity() {
        return App.builder()
                .name(name)
                .type(type)
                .isActive(true)
                .userId(userId)
                .build();
    }


}
