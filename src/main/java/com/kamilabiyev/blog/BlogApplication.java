package com.kamilabiyev.blog;

import com.kamilabiyev.blog.seeder.DataSeeder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class BlogApplication {
    private final DataSeeder dataSeeder;
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @PostConstruct
    public void init() {
        dataSeeder.seed();
    }

}
