package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.projection.PersonProjection;
import com.example.demo.repository.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public Page<PersonProjection> getAllPersons(Pageable pageable) {
        return personRepository.findAllProjectedBy(pageable);
    }

    public Person updatePerson(Integer id, Person personDetails) {
        Person person = personRepository.findById(id).orElseThrow();
        person.setName(personDetails.getName());
        person.setLastname(personDetails.getLastname());
        return personRepository.save(person);
    }

    public void deletePerson(Integer id) {
        personRepository.deleteById(id);
    }
}