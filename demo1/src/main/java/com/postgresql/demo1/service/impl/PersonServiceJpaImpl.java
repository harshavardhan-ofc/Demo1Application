package com.postgresql.demo1.service.impl;
import com.postgresql.demo1.entity.Person;
import com.postgresql.demo1.repository.PersonRepo;
import com.postgresql.demo1.service.IPersonServiceJpa;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.List;

//@Service
@Service
public class PersonServiceJpaImpl implements IPersonServiceJpa {



        @Autowired
        private PersonRepo personRepo;

        @Override
        public List<Person> getAllPersons() {
            return personRepo.findAll();
        }
    @Override
    public Person getPersonById(Long id) {
        return personRepo.findById(id).orElse(null);
    }


    @Override
        public Person addPerson(Person person) {
           return personRepo.save(person);
        }

        @Override
        public Person updatePerson(Long id, Person person) {
            return personRepo.findById(id).map(existing -> {
                existing.setName(person.getName());
                existing.setSubject(person.getSubject());
                existing.setGrade(person.getGrade());
                return personRepo.save(existing);
            }).orElse(null);
        }

        @Override
        public int deletePerson(Long id) {
            if(personRepo.existsById(id)) {
                personRepo.deleteById(id);
                return 1;
            }
            return 0;
        }

        @Override
        public Person updatePartialPerson(Long id, Person person) {
            return personRepo.findById(id).map(existing -> {
                if(person.getName() != null) existing.setName(person.getName());
                if(person.getSubject() != null) existing.setSubject(person.getSubject());
                if(person.getGrade() != null) existing.setGrade(person.getGrade());
                return personRepo.save(existing);
            }).orElse(null);
        }
    }


