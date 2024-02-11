package com.example.appservice.entity;

import com.example.appservice.dto.AppRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "app")
public class App {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String name;

    @Column
    private String userName;

    @Column
    private String type;

    @Column
    private Boolean isActive;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column
    private String userId;

}
