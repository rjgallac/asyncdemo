package uk.co.sheffieldwebprogrammer.asyncdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class CallProcessService {

    private RestTemplate restTemplate = new RestTemplate();

    @Async
    public CompletableFuture<String> doAsync1(String test){
        //do something
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://127.0.0.1:8081/test1?test=" + test, String.class);
        return CompletableFuture.completedFuture(forEntity.getBody());
    }

    @Async
    public CompletableFuture<String> doAsync2(String test){
        //do something
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://127.0.0.1:8081/test2?test=" + test, String.class);
        return CompletableFuture.completedFuture(forEntity.getBody());
    }

    @Async
    public CompletableFuture<String> doAsync3(String test){
        //do something
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://127.0.0.1:8081/test3?test=" + test, String.class);
        return CompletableFuture.completedFuture(forEntity.getBody());
    }
}
