//package com.postgresql.demo1.service.impl;
//
//import com.postgresql.demo1.entity.Person;
//
//import java.util.List;
//
//public interface IPersonServiceJpa {
//    List<Person>getAllPersons();
//    Person addPerson(Person person);
//
//    Person getPersonById(Long id);
//    Person updatePerson(Long id, Person person);
//    int deletePerson( Long id);
//    Person updatePartialPerson(Long id,Person person);
//
//     void deletePerson(Long id);
//
//}
package com.postgresql.demo1.service;

public interface IPersonServiceJpa extends IPersonService {
    // JPA-specific methods (if any) go here
}

