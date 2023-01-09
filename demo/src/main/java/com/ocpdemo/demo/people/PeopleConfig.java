package com.ocpdemo.demo.people;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.AUGUST;
import static java.time.Month.OCTOBER;
@Configuration
public class PeopleConfig {

    @Bean
    CommandLineRunner commandLineRunner (PeopleRepository peopleRepository){
        return args -> {
          People Mathan = new People(
                  1L,
                  "Mathan",
                  "Mathan@foo.com",
                  "USA",
                  LocalDate.of(1993, AUGUST, 19)
          );
          People Jacko = new People(
                  2L,
                  "Jacko",
                  "Jacko@foo.com",
                  "UK",
                  LocalDate.of(1994, OCTOBER, 12)
          );
           People Jack = new People(
                  3L,
                  "Jack",
                  "Jack@foo.com",
                  "Scotland",
                  LocalDate.of(1993, OCTOBER, 11)
          );
          peopleRepository.saveAll(List.of(Mathan, Jacko, Jack));
        };
    }
}
