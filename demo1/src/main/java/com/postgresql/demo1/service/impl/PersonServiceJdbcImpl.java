package com.postgresql.demo1.service.impl;
import com.postgresql.demo1.dao.PersonDAOImpl;
import com.postgresql.demo1.entity.Person;
//import com.postgresql.demo1.service.PersonService;
import com.postgresql.demo1.service.IPersonServiceJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonServiceJdbcImpl implements IPersonServiceJdbc {


    @Autowired
    private PersonDAOImpl personDAO;



    @Override
    public List<Person> getAllPersons() {
       return personDAO.getAllPersons();
    }


    @Override
    public Person addPerson(Person person) {
        personDAO.addPerson(person);
       return person;
    }

    @Override
    public Person getPersonById(Long id) {
        return personDAO.getPersonById(id);
    }


    @Override
    public Person updatePerson(Long id, Person person) {
        return  personDAO.updatePerson(id, person);
    }

    @Override
    public int deletePerson(Long id){
        return personDAO.deletePerson(id);
    }

    public Person updatePartialPerson(Long id,Person person ){

        return personDAO.updatePartialPerson(id, person);
    }



}
