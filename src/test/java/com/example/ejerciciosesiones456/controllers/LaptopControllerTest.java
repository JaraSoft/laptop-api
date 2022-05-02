package com.example.ejerciciosesiones456.controllers;

import com.example.ejerciciosesiones456.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder builder;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        builder = builder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(builder);
    }

    @Test
    void getAllLaptops() {
        ResponseEntity<Laptop[]> response =
                testRestTemplate.getForEntity("/api/laptops", Laptop[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200,
                response.getStatusCode().value());
        List<Laptop> laptops = Arrays.asList(response.getBody());
        assertEquals(1, laptops.size());
    }

    @Test
    void saveLaptop() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json = "{" +
                "\"name\": \"Aurora\"," +
                "\"brand\": \"AlienWare\"," +
                "\"model\": \"R13\"," +
                "\"price\": 6000" +
                "}";
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);
        Laptop result = response.getBody();
        assert result != null;
        assertEquals(1, result.getId());
        assertEquals("Aurora", result.getName());
        assertEquals("AlienWare", result.getBrand());
        assertEquals("R13", result.getModel());
        assertEquals(6000, result.getPrice());



    }

    @Test
    void findOneById() {
        ResponseEntity<Laptop> response =
                testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }
/*
    @Test
    void updateLaptop() {
        saveLaptop();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json = "{" +
                "\"id\": 1," +
                "\"name\": \"Aurora\"," +
                "\"brand\": \"AlienWare\"," +
                "\"model\": \"R13\"," +
                "\"price\": 5000" +
                "}";
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);
        Laptop result = response.getBody();
        assertEquals(1L, result.getId());
        assertEquals("Aurora", result.getName());
        assertEquals("AlienWare", result.getBrand());
        assertEquals("R13", result.getModel());
        assertEquals(5000, result.getPrice());

    }

    @Test
    void delete() {
        saveLaptop();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops/1", HttpMethod.DELETE, request, Laptop.class);
        Laptop result = (response.getBody());
        assertNull(result);
    }

    @Test
    void deleteAll() {
        saveLaptop();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<Void> response = testRestTemplate.exchange("/api/laptops", HttpMethod.DELETE, request, Void.class);
        Void result = (response.getBody());
        assertNull(result);


    }
 */
}
