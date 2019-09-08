package com.lawencon.linov.outsource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan( basePackageClasses = {
        OutsourceApplication.class,
        Jsr310JpaConverters.class
})
public class OutsourceApplication {

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Jakarta"));
    }

    public static void main(String[] args) {
        SpringApplication.run(OutsourceApplication.class, args);
    }

}
