package com.javachen;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RestTemplateBasicLiveTest {

    private RestTemplate restTemplate;
    private static final String URL = "http://localhost:8000/actuator";

    @Before
    public void beforeTest() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void givenResourceUrl_whenSendGetForRequestEntity_thenStatusOk() throws IOException {
        final ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

}
