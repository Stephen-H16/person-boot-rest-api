package com.example.person.controller;

import com.example.person.entity.Address;
import com.example.person.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/address")
public class AddressController {
	
    @Autowired
    AddressService addressService;

    @GetMapping("")
    public List<Address> list() {
        return addressService.listAllAddresses();
    }

    @PostMapping("/{personId}")
    public void add(@PathVariable Integer personId, @RequestBody List<Address> address) {
    	addressService.saveAddress(personId, address);
    }
    
    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody Address address) {
        try {
            addressService.updateAddress(address);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{addressId}")
    public void delete(@PathVariable Integer addressId) {
    	addressService.deleteAddress(addressId);
    }
        
    @GetMapping("/{addressId}")
    public ResponseEntity<Address> get(@PathVariable Integer addressId) {
        try {
            Address address = addressService.getAddress(addressId);
            return new ResponseEntity<Address>(address, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }
    }
    
}
