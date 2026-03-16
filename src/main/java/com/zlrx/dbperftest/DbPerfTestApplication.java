package com.zlrx.dbperftest;

import com.zlrx.dbperftest.vehicle.NotInject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = NotInject.class
        )
)
public class DbPerfTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbPerfTestApplication.class, args);
    }

}
