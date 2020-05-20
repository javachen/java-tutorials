package com.javachen.springbootsecurity.whitelist_ip;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = WhitelistIpApplication.class)
public class WhitelistIpConfigurationTest {
    TestRestTemplate restTemplate;
    URL base;

    @LocalServerPort
    int port;

    @Before
    public void setUp() throws MalformedURLException {
        restTemplate = new TestRestTemplate("user", "password");
    }

    @Test
    public void whenWhiltelistIpRequestsHomePage_ThenSuccess() throws IllegalStateException, IOException {
        base = new URL("http://192.168.2.111:" + port);
        ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response
                .getBody()
                .contains("Spring Security"));
    }

    @Test
    public void whenNotWhiltelistIpRequestsHomePage_ThenForbiddenPage() throws IllegalStateException, IOException {
        base = new URL("http://localhost:" + port);

        ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertFalse(response
                .getBody()
                .contains("Spring Security"));
    }
}
