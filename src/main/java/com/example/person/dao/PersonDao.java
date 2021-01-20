package com.example.person.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.person.entity.Person;

public interface PersonDao extends JpaRepository<Person, Integer> {
	
}
