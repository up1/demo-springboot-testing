# Demo Spring Boot Testing

### Setup grafle project :: `build.gradle`
```
plugins {
	id 'org.springframework.boot' version '2.1.7.RELEASE'
	id 'java'
	id 'jacoco'
}

bootJar {
	archiveFileName = "demo.${archiveExtension.get()}"
}
```

Run in development mode
```
$gradlew bootRun 
```

Run with Jar file
```
$gradlew bootJar
$java -jar build/libs/demo.jar 
```



### 1. Spring Boot Testing (@SpringBootTest)
```
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerSpringBootTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void success_getEmployee_id_1() {
        // Act
        EmployeeResponse response = restTemplate.getForObject("/employee/1", EmployeeResponse.class);

        // Assert
        assertEquals(1, response.getId());
        assertEquals("Somkiat Pui", response.getName());
    }
}
```

### 2. Spring MVC Testing (@WebMvcTest)
```
@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerMVCTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void success_getEmployee_by_id_1() throws Exception {
        // Act
        MvcResult result = this.mvc.perform(get("/employee/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        // Convert JSON message to Object
        ObjectMapper mapper = new ObjectMapper();
        EmployeeResponse response = mapper.readValue(result.getResponse().getContentAsString(), EmployeeResponse.class);

        // Assert
        assertEquals(1, response.getId());
        assertEquals("Somkiat Pui", response.getName());
    }

}
```

### 3. Create Service Layer
```
@Service
public class EmployeeService {

    public EmployeeResponse getBy(int id) {
        return new EmployeeResponse(1, "Somkiat Pui");
    }

}
```

### 4. Fix Spring MVC Testing with @MockBean and Stubbing
```
@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerMVCTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void success_getEmployee_by_id_1() throws Exception {
        // Arrange
        given(this.employeeService.getBy(1))
                .willReturn(new EmployeeResponse(1, "Somkiat Pui"));

        // Act
        MvcResult result = this.mvc.perform(get("/employee/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        // Convert JSON message to Object
        ObjectMapper mapper = new ObjectMapper();
        EmployeeResponse response = mapper.readValue(result.getResponse().getContentAsString(), EmployeeResponse.class);

        // Assert
        assertEquals(1, response.getId());
        assertEquals("Somkiat Pui", response.getName());
    }

}
```

### 5. Spring Data JPA Testing (@DataJpaTest)
Entity :: Employee.java
```
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;

    public Employee() {
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
```

EmployeeRepository.java
```
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
```

Spring Data JPA Testing
```
@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void success_get_employee_by_id_1() {
        // Arrange
        this.entityManager.persist(new Employee("Somkiat", "Pui"));

        // Act
        Optional<Employee> employee = employeeRepository.findById(1);

        // Assert
        assertEquals("Somkiat", employee.get().getFirstName());
        assertEquals("Pui", employee.get().getLastName());
    }

}
```

### 6. Service + Repository
```
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeResponse getBy(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return new EmployeeResponse(1, String.format("%s %s", employee.get().getFirstName(), employee.get().getLastName()));
    }

}
```
### 7. Fix Spring Boot Testing with @MockBean and Stubbing
```
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerSpringBootTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void success_getEmployee_id_1() {
        // Arrange
        given(this.employeeService.getBy(1))
                .willReturn(new EmployeeResponse(1, "Somkiat Pui"));

        // Act
        EmployeeResponse response = restTemplate.getForObject("/employee/1", EmployeeResponse.class);

        // Assert
        assertEquals(1, response.getId());
        assertEquals("Somkiat Pui", response.getName());
    }
}
```

### 8. Unit testing with Service Layer
```
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService = new EmployeeService();

    @Test
    public void success_with_get_by_id_1() {
        // Arrange
        given(this.employeeRepository.findById(1))
                .willReturn(Optional.of(new Employee("Somkiat", "Pui")));

        employeeService.setEmployeeRepository(employeeRepository);

        // Act
        EmployeeResponse response = employeeService.getBy(1);

        // Assert
        assertEquals(1, response.getId());
        assertEquals("Somkiat Pui", response.getName());
    }
    
}
```

### 9. Gateway Testing with WireMock (RESTful API)
Using [library wiremock](https://github.com/tomakehurst/wiremock)
