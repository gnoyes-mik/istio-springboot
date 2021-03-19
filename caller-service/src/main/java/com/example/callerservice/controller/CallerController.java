package com.example.callerservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/caller")
public class CallerController {

    private RestTemplate restTemplate = new RestTemplate();
    private String version = "V1";

    @GetMapping("/ping")
    public String ping() {
        String response = restTemplate.getForObject("http://callme-service:8080/callme/ping", String.class);
        return "I'm caller-service " + version + ". Calling... " + response;
    }

    @GetMapping("/ping-with-random-error")
    public ResponseEntity<String> pingWithRandomError() {
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("http://callme-service:8080/callme/ping-with-random-error", String.class);
        return new ResponseEntity<>("I'm caller-service " + version + ". Calling... " + responseEntity.getBody(), responseEntity.getStatusCode());
    }

    @GetMapping("/ping-with-random-delay")
    public String pingWithRandomDelay() {
        String response = restTemplate.getForObject("http://callme-service:8080/callme/ping-with-random-delay", String.class);
        return "I'm caller-service " + version + ". Calling... " + response;
    }

}
