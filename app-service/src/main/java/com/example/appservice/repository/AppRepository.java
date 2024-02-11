package com.example.appservice.repository;

import com.example.appservice.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository extends JpaRepository<App, String> {
}
