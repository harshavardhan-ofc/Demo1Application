//package com.postgresql.demo1.service.impl;
//
//public interface IPersonService {
package com.postgresql.demo1.service;

import com.postgresql.demo1.entity.Person;
import java.util.List;

    public interface IPersonService {
        List<Person> getAllPersons();
        Person addPerson(Person person);
        Person getPersonById(Long id);
        Person updatePerson(Long id, Person person);
        int deletePerson(Long id);
        Person updatePartialPerson(Long id, Person person);
    }


