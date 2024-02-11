package com.example.appservice.controller;

import com.example.appservice.dto.AppRequestDto;
import com.example.appservice.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app")
public class AppController {


    private final AppService appService;

    @PostMapping("/create")
    public ResponseEntity<?> createApp(@RequestBody AppRequestDto appRequestDto, @RequestParam("id") String id) {
        appService.createApp(appRequestDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
