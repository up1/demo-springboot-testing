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

### 2. Spring MVC Testing (@WebMvcTest)

### 3. Spring Data JPA Testing (@DataJpaTest)

### 4. Unit Testing  at Service Layer

### 5. Gateway Testing with WireMock
