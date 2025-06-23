package com.example.demo.repository;

import com.example.demo.entity.Person;
import com.example.demo.projection.PersonProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("SELECT p.id as id, p.name as name, p.lastname as lastname FROM Person p")
    Page<PersonProjection> findAllProjectedBy(Pageable pageable);
}