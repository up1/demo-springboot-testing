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

### 3. Spring Data JPA Testing (@DataJpaTest)

### 4. Unit Testing  at Service Layer

### 5. Gateway Testing with WireMock
