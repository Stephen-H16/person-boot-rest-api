package com.example.person.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "id")
	private int id;
	@Column(name = "person_id")
    private int personId;
	@Column(name = "street")
    private String street;
	@Column(name = "city")
    private String city;
	@Column(name = "state")
    private String state;
	@Column(name = "postal_code")
    private String postalCode;

    public Address() {
    }
    
    public Address(int personId, String street, String city, String state, String postalCode) {
    	this.personId = personId;
		this.street = street;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
	}
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

	public void setId(int id) {
		this.id = id;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", personId=" + personId + ", street=" + street + ", city=" + city + ", state="
				+ state + ", postalCode=" + postalCode + "]";
	}

}
