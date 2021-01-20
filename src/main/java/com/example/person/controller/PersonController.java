package com.example.person.controller;

import com.example.person.entity.Person;
import com.example.person.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/person")
public class PersonController {
	
    @Autowired
    PersonService personService;

    @GetMapping("")
    public List<Person> list() {
        return personService.listAllPersons();
    }

    @PostMapping("")
    @Transactional
    public void add(@RequestBody Person user) {
    	personService.savePerson(user);
    }
    
    @PutMapping("")
    @Transactional
    public ResponseEntity<?> update(@RequestBody Person person) {
        try {
            Person personExists = personService.getPerson(person.getId());
            personExists.setFirstName(null == person.getFirstName() || StringUtils.isEmpty(person.getFirstName()) ? "" : person.getFirstName());
            personExists.setLastName(null == person.getLastName() || StringUtils.isEmpty(person.getLastName()) ? "" : person.getLastName());
            personService.savePerson(personExists);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{personId}")
    @Transactional
    public void delete(@PathVariable Integer id) {
    	personService.deletePerson(id);
    }
        
    @GetMapping("/{personId}")
    public ResponseEntity<Person> get(@PathVariable Integer id) {
        try {
            Person user = personService.getPerson(id);
            return new ResponseEntity<Person>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/count")
    public Integer personCount() {
        return personService.personCount();
    }
    
}
