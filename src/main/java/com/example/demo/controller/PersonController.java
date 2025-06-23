package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.projection.PersonProjection;
import com.example.demo.projection.PersonDTO;
import com.example.demo.repository.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    public Person createPerson(@RequestBody PersonDTO personDTO) {
        Person person = new Person(personDTO.getName(), personDTO.getLastname());
        return personRepository.save(person);
    }

    @GetMapping
    public Page<PersonProjection> getAllPersons(Pageable pageable) {
        return personRepository.findBy(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id) {
        return personRepository.findById(id)
                .map(person -> ResponseEntity.ok(person))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody Person personDetails) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setName(personDetails.getName());
                    person.setLastname(personDetails.getLastname());
                    return ResponseEntity.ok(personRepository.save(person));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Integer id) {
        return personRepository.findById(id)
                .map(person -> {
                    personRepository.delete(person);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}