package com.postgresql.demo1.controller;

import com.postgresql.demo1.entity.Person;
import com.postgresql.demo1.service.impl.PersonServiceJpaImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jpa/persons")
public class JpaPersonController {
    private static final Logger logger = LogManager.getLogger(JpaPersonController.class);

    @Autowired
    private PersonServiceJpaImpl personService;
//====================================================================================
//    @PostMapping("/addPerson")
//    public  ResponseEntity<Person> addPerson(@RequestBody Person person) {
//        logger.debug("Received request to add person: {}", person);
//        try {
//            if (person == null || person.getName() == null || person.getName().trim().isEmpty()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//            }
//            personService.addPerson(person);
//            logger.info("Person Added : {}",person.getName());
//            return ResponseEntity.status(HttpStatus.CREATED).build();
//        } catch (Exception e){
//            logger.error("Error while adding person",e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//===================================================================
// post mapping
@PostMapping("/addPerson")
public ResponseEntity<Person> addPerson(@RequestParam String name, @RequestParam int age) {
    logger.debug("Received request to add person using request params - Name: {}, Age: {}", name, age);
    try {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Person person = new Person();
        person.setName(name);
        person.setAge(age);

        personService.addPerson(person);
        logger.info("Person added successfully via request params: {}", name);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);

    } catch (Exception e) {
        logger.error("Error while adding person using request params", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
// get mapping
    @GetMapping("/getPersonById/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        logger.debug("Received request to fetch person with ID: {}", id);
        if(id<=0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            Person person = personService.getPersonById(id);
            logger.debug("Fetched person object: {}", person);
            if (person == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            logger.info("successfully fetched person with ID : {}", id);
            return ResponseEntity.ok(person);
        }
        catch (Exception e) {
            logger.error("error while fetching the person by Id : {}", id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("updatePerson/{id}")
    public ResponseEntity<Person> updatePersonBy(@PathVariable Long id,  @RequestBody Person person) {
        logger.debug("Received request to update person with ID: {}, new data: {}", id, person);
        if (id <= 0 || person == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            Person updated = personService.updatePerson(id, person);
            if (updated == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            logger.error("Error while updating person with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/deletePersonBy/{id}")
    public ResponseEntity<String> deletePersonBy(@PathVariable Long id) {
        logger.debug("Received request to delete person with ID: {}", id);
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            int deleted = personService.deletePerson(id);
            if (deleted == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found");
            }
            logger.info("SUCCESSFULLY DELETED WITH ID : {}", id);
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception e) {
            logger.error("Error while deleting person with id :{}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error ");
        }
    }
    @PatchMapping("/patchPerson/{id}")
    public ResponseEntity<Person> updatePartialPerson(@PathVariable Long id, Person person) {
        logger.debug("Received request to partially update person with ID: {}, fields: {}", id, person);
        if (id <= 0 || person == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            Person updatePartialPerson = personService.updatePartialPerson(id, person);
            if (updatePartialPerson == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            logger.info("Partially updated person with ID :{}", id);
            return ResponseEntity.ok(updatePartialPerson);
        } catch (Exception e) {
            logger.error("Error while partially updating with ID :{}", id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
