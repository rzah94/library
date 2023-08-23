package com.example.libproject.service;

import com.example.libproject.domain.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAllPerson();
    Person getPerson(long id);
    Person updatePerson(Person person);
    Person createPerson(Person person);
    void deletePerson(long id);
}
