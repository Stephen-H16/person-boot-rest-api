DROP TABLE IF EXISTS person;
  
CREATE TABLE person (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL
);

INSERT INTO person (first_name, last_name) VALUES ('Stephen', 'Hoey'), ('Bill', 'Gates');

DROP TABLE IF EXISTS address;

CREATE TABLE address (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  person_id INT,
  street VARCHAR(250) NOT NULL,
  city VARCHAR(250) NOT NULL,
  state VARCHAR(250) NOT NULL,
  postal_code VARCHAR(250) NOT NULL,
  foreign key (person_id) references person(id)
);

INSERT INTO address (person_id, street, city, state, postal_code) VALUES (1, 'test', 'test', 'test', 'test');