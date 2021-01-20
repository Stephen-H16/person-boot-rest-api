package com.example.person.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.person.entity.Address;

public interface AddressDao extends JpaRepository<Address, Integer> {
	
	
	
}
