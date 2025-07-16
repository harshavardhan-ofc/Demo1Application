package com.postgresql.demo1.dao;

import com.postgresql.demo1.entity.Person;

import java.util.List;


public interface IPersonDAO {


   void addPerson(Person person);

   List<Person> getAllPersons();


    Person getPersonById(Long id);


    Person updatePerson(Long id, Person person);

    int deletePerson( Long id);

    Person updatePartialPerson(Long id,Person person);


}
