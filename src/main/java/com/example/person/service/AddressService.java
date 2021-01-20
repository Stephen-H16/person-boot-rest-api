package com.example.person.service;

import com.example.person.dao.AddressDao;
import com.example.person.entity.Address;
import com.example.person.entity.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class AddressService {
	
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private PersonService personService;
    
    public List<Address> listAllAddresses() {
        return addressDao.findAll();
    }

    public ResponseEntity<List<Address>> saveAddress(Integer personId, List<Address> addresses) {
    	try {
            Person user = personService.getPerson(personId);
            if(!CollectionUtils.isEmpty(addresses) && null != user) {
            	addresses.forEach(address -> {
            		saveAddress(user, address);
            	});
            }
            return new ResponseEntity<List<Address>>(addresses, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<List<Address>>(HttpStatus.NOT_FOUND);
        }
    }

	private void saveAddress(Person user, Address address) {
		Address newAddress = new Address();
		newAddress.setPersonId(user.getId());
		newAddress.setState(address.getState());
		newAddress.setCity(address.getCity());
		newAddress.setStreet(address.getStreet());
		newAddress.setPostalCode(address.getPostalCode());
		addressDao.save(newAddress);
	}
	
	public ResponseEntity<Address> updateAddress(Address address) {
    	try {
            addressDao.save(address);
            return new ResponseEntity<Address>(address, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }
    }

    public Address getAddress(Integer id) {
        return addressDao.findById(id).get();
    }

    public void deleteAddress(Integer id) {
    	addressDao.deleteById(id);
    }
}
