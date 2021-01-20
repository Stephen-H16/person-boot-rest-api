package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;

import com.example.person.dao.PersonDao;
import com.example.person.entity.Person;
import com.example.person.service.PersonService;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTests {

	@InjectMocks
	private PersonService personService;
	
	@Mock
	private PersonDao personDao;
	
	@Test
	public void whenFindAllListAllUsers() {
		Person person = new Person("Stephen", "Hoey");
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		Mockito.when(personService.listAllPersons()).thenReturn(persons);
		List<Person> personList = personService.listAllPersons();
		Mockito.verify(personDao, Mockito.times(1)).findAll();
		Assert.assertTrue(personList.size() == 1);
	}
	
	@Test
	public void whenSaveShouldSave() {
		Person person = new Person("Stephen", "Hoey");
		Mockito.when(personDao.save(Mockito.any(Person.class))).thenReturn(person);
		personService.savePerson(person);
		Mockito.verify(personDao, Mockito.times(1)).save(Mockito.any(Person.class));
	}
	
	@Test(expected = NoSuchElementException.class)
	public void whenSearchPersonShouldReturnPerson() {
		Person personFind = personService.getPerson(1);
		Mockito.verify(personDao, Mockito.times(1)).findById(Mockito.anyInt()).get();
		Assert.assertTrue(personFind != null);
	}
	
	@Test
	public void whenRemovePersonShouldDelete() {
		personService.deletePerson(Mockito.anyInt());
		Mockito.verify(personDao, Mockito.times(1)).deleteById(Mockito.anyInt());
	}
	
	@Test
	public void whenCountPersonShouldReturnCount() {
		Person person = new Person("Stephen", "Hoey");
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		Mockito.when(personDao.findAll()).thenReturn(persons);
		Integer personCount = personService.personCount();
		Mockito.verify(personDao, Mockito.times(2)).findAll();
		Assert.assertTrue(personCount == 1);
	}
	
	@Test
	public void whenCountPersonShouldReturnEmpty() {
		Mockito.when(personDao.findAll()).thenReturn(null);
		Integer personCount = personService.personCount();
		Mockito.verify(personDao, Mockito.times(1)).findAll();
		Assert.assertTrue(personCount == 0);
	}
    
}
