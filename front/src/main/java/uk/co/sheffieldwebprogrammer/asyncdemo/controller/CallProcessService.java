package uk.co.sheffieldwebprogrammer.asyncdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class CallProcessService {

    private RestTemplate restTemplate = new RestTemplate();

    @Async
    public CompletableFuture<String> doAsync(String test, long sleep){
        long start = System.currentTimeMillis();

        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://127.0.0.1:8081/test?test=" + test + "&sleep=" + sleep, String.class);
        log.info("Elapsed time rest call: " + (System.currentTimeMillis() - start));

        return CompletableFuture.completedFuture(forEntity.getBody());
    }
}
