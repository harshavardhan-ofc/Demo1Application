package com.postgresql.demo1.controller;

import com.postgresql.demo1.entity.Person;
import com.postgresql.demo1.service.impl.PersonServiceJdbcImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jdbc/persons")
public class JdbcPersonController {

    private static final Logger logger = LogManager.getLogger(JdbcPersonController.class);

    @Autowired
    private PersonServiceJdbcImpl personService;
//
//=====================================================================================    // POST: Add Person
    @PostMapping("/addPerson")
    public ResponseEntity<Void> addPerson(@RequestBody Person person) {
        logger.debug("Received request to add person: {}", person);
        try {
            if (person == null || person.getName() == null || person.getName().trim().isEmpty()) {
                logger.warn("Invalid person data provided");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            personService.addPerson(person);
            logger.info("Person added successfully: {}", person.getName());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            logger.error("Error while adding person", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
//    ===========================================================================================
//post using req param
//@PostMapping("/addPerson")
//public ResponseEntity<Void> addPerson(@RequestParam String name, @RequestParam int age) {
//    logger.debug("Received request to add person using params - Name: {}, Age: {}", name, age);
//    try {
//        if (name == null || name.trim().isEmpty()) {
//            logger.warn("Invalid name provided");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//        Person person = new Person();
//        person.setName(name);
//        person.setAge(age);
//        personService.addPerson(person);
//        logger.info("Person added successfully via params: {}", name);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    } catch (Exception e) {
//        logger.error("Error while adding person via params", e);
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }
//}

    // GET: All persons
    @GetMapping("/getPersons")
    public ResponseEntity<List<Person>> getAllPersons() {
        logger.debug("Received request to fetch all persons");
        try {
            List<Person> persons = personService.getAllPersons();
            logger.info("Fetched all persons, count: {}", persons.size());
            return ResponseEntity.ok(persons);
        } catch (Exception e) {
            logger.error("Error while fetching persons", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET: Person by ID
    @GetMapping("/getPersonById/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        if (id <= 0) {
            logger.warn("Invalid ID received: {}", id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            Person person = personService.getPersonById(id);
            if (person == null) {
                logger.warn("Person not found with ID: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            logger.info("Fetched person with ID: {}", id);
            return ResponseEntity.ok(person);
        } catch (Exception e) {
            logger.error("Error while fetching person with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // PUT: Update a person
    @PutMapping("/updatePerson/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        logger.debug("Received request to update person with ID: {}, new data: {}", id, person);
        if (id <= 0 || person == null) {
            logger.warn("Invalid request for update. ID: {}, Person: {}", id, person);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            Person updated = personService.updatePerson(id, person);
            if (updated == null) {
                logger.warn("Person not found for update with ID: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            logger.info("Updated person with ID: {}", id);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            logger.error("Error while updating person with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DELETE: Delete a person
    @DeleteMapping("/deletePerson/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        logger.debug("Received request to delete person with ID: {}", id);
        if (id <= 0) {
            logger.warn("Invalid delete request with ID: {}", id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID");
        }
        try {
            int deleted = personService.deletePerson(id);
            if (deleted == 0) {
                logger.warn("No person found to delete with ID: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found");
            }
            logger.info("Deleted person with ID: {}", id);
            return ResponseEntity.ok("Person deleted successfully");
        } catch (Exception e) {
            logger.error("Error while deleting person with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    // PATCH: Update partial fields of a person
    @PatchMapping("/updatePartialPerson/{id}")
    public ResponseEntity<Person> updatePartialPerson(@PathVariable Long id,
                                                      @RequestBody Person person) {
        logger.debug("Received request to partially update person with ID: {}, fields: {}", id, person);
        if (id <= 0 || person == null) {
            logger.warn("Invalid patch request. ID: {}, Person: {}", id, person);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            Person updated = personService.updatePartialPerson(id, person);
            if (updated == null) {
                logger.warn("No person found to patch with ID: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            logger.info("Patched person with ID: {}", id);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            logger.error("Error while patching person with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}










