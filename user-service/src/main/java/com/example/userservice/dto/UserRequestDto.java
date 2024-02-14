package com.example.userservice.dto;

import com.example.userservice.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequestDto {

    @Getter
    public static class Register {

        private String name;

        private String password;

        public User toEntity() {
            return User.builder()
                    .name(name)
                    .password(password)
                    .build();
        }
    }

}
