package com.ocpdemo.demo.people;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeopleRepository extends JpaRepository<People, Long> {
    Optional<People> findPeopleByName(String Name);
    Optional<People> findPeopleByPlace(String Place);
}
