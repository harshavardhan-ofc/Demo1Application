//package com.postgresql.demo1.service.impl;
//
//import com.postgresql.demo1.entity.Person;
//
//import java.util.List;
//
//public interface IPersonServiceJdbc {
//    List<Person> getAllPersons();
//    void addPerson(Person person);
//
//    Person getPersonById(Long id);
//    Person updatePerson(Long id, Person person);
//    int deletePerson( Long id);
//    Person updatePartialPerson(Long id,Person person);
//
// void deletePerson(Long id);
//
//}
package com.postgresql.demo1.service;

public interface IPersonServiceJdbc extends IPersonService {
    // JDBC-specific methods (if any) go here
}

