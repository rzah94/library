package com.example.libproject.service;

import com.example.libproject.domain.Person;
import com.example.libproject.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAllPerson() {
        return (List<Person>) personRepository.findAll();
    }

    @Override
    public Person getPerson(long id) {
        return personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id:" + id));
    }

    @Override
    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void deletePerson(long id) {
        personRepository.deleteById(id);
    }
}
