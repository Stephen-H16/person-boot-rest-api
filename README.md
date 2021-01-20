This application uses H2 as an in memory database which creates two persons initially and assigns one address to the first user. 

To build project -> mvn clean install
To run project -> Run PersonApplication.java



Person Api -> 

//List All
@GetMapping("http://localhost:8080/person")
public List<Person> list();
 
//Add Person
@PostMapping("http://localhost:8080/person");
body - > {"firstName" : "test", "lastName" : "test"}
public void add(@RequestBody Person user);

//Update Person
@PutMapping("http://localhost:8080/person")
body - > {"id" : 1, "firstName" : "test", "lastName" : "test"}
public ResponseEntity<?> update(@RequestBody Person person);

// Delete Person
@DeleteMapping("http://localhost:8080/person/{personId}")
public void delete(@PathVariable Integer addressId);
    
// Get Person
@GetMapping(""http://localhost:8080/person/{personId}")
public ResponseEntity<Person> get(@PathVariable Integer id);

@GetMapping(""http://localhost:8080/person/count")
public Integer personCount();



Address Api -> 

//List All
@GetMapping("http://localhost:8080/address")
public List<Person> list();
 
//Add Address
@PostMapping("http://localhost:8080/address/{personId}");
body - > 
[
    {
        "id": 1,
        "personId": 1,
        "street": "test",
        "city": "test",
        "state": "test",
        "postalCode": "test"
    }
]
public void add(@PathVariable Integer personId, @RequestBody List<Address> address);

//Update Address
@PutMapping("http://localhost:8080/person")
body - >
{
    "id": 1,
    "personId": 1,
    "street": "test",
    "city": "test",
    "state": "test",
    "postalCode": "test"
}
public ResponseEntity<?> update(@RequestBody Address address);

// Delete Address
@DeleteMapping("http://localhost:8080/person/{addressId}")
public void delete(@PathVariable Integer addressId);
    
// Get Address
@GetMapping(""http://localhost:8080/person/{addressId}")
public ResponseEntity<Address> get(@PathVariable Integer addressId);