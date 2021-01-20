package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import com.example.person.dao.AddressDao;
import com.example.person.dao.PersonDao;
import com.example.person.entity.Address;
import com.example.person.entity.Person;
import com.example.person.service.AddressService;
import com.example.person.service.PersonService;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTests {

	@InjectMocks
	private AddressService addressService;
	
	@Mock
	private AddressDao addressDao;
	
	@Mock
    private PersonService personService;
    
	@Test
	public void whenFindAllListAllAddresses() {
		Address address = new Address(1, "test", "test", "test", "test");
		List<Address> addresses = new ArrayList<>();
		addresses.add(address);
		Mockito.when(addressService.listAllAddresses()).thenReturn(addresses);
		List<Address> addressList = addressService.listAllAddresses();
		Mockito.verify(addressDao, Mockito.times(1)).findAll();
		Assert.assertTrue(addressList.size() == 1);
	}
	
	@Test
	public void whenSaveShouldSave() {
		Address address = new Address(1, "test", "test", "test", "test");
		List<Address> addresses = new ArrayList<>();
		addresses.add(address);
		Mockito.when(addressDao.save(Mockito.any(Address.class))).thenReturn(address);
		Mockito.when(personService.getPerson(Mockito.anyInt())).thenReturn(new Person("Stephen", "Hoey"));
		addressService.saveAddress(1, addresses);
		Mockito.verify(addressDao, Mockito.times(1)).save(Mockito.any(Address.class));
	}
	
	@Test(expected = NoSuchElementException.class)
	public void whenSearchAddressShouldReturnAddress() {
		Address addressFind = addressService.getAddress(1);
		Mockito.verify(addressDao, Mockito.times(1)).findById(Mockito.anyInt()).get();
		Assert.assertTrue(addressFind != null);
	}
	
	@Test
	public void whenUpdateAddressShouldReturnAddress() {
		Address address = new Address(1, "test", "test", "test", "test");
		ResponseEntity<Address> addressFind = addressService.updateAddress(address);
		Mockito.verify(addressDao, Mockito.times(1)).save(Mockito.any(Address.class));
		Assert.assertTrue(addressFind != null);
	}
	
	@Test
	public void whenRemovePersonShouldDelete() {
		addressService.deleteAddress(Mockito.anyInt());
		Mockito.verify(addressDao, Mockito.times(1)).deleteById(Mockito.anyInt());
	}
	
}
