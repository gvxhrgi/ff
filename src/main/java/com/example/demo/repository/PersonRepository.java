package com.example.demo.repository;

import com.example.demo.entity.Person;
import com.example.demo.projection.PersonProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Page<PersonProjection> findBy(Pageable pageable);
}