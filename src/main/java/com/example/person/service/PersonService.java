package com.example.person.service;

import com.example.person.dao.PersonDao;
import com.example.person.entity.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;

import java.util.List;
@Service
@Transactional
public class PersonService {
	
    @Autowired
    private PersonDao personDao;
    
    public List<Person> listAllPersons() {
        return personDao.findAll();
    }

    public void savePerson(Person person) {
    	personDao.save(person);
    }

    public Person getPerson(Integer id) {
        return personDao.findById(id).get();
    }

    public void deletePerson(Integer id) {
    	personDao.deleteById(id);
    }
    
    public int personCount() {
    	if(!CollectionUtils.isEmpty(personDao.findAll())) {
    		return personDao.findAll().size();
    	}else {
    		return 0;
    	}
    }
}
