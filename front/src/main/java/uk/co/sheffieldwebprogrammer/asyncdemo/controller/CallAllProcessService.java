package uk.co.sheffieldwebprogrammer.asyncdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CallAllProcessService {

    @Autowired
    private CallProcessService callProcessService;

    public void callAll(String test){
        CompletableFuture<String> stringCompletableFuture1 = callProcessService.doAsync1(test);
        CompletableFuture<String> stringCompletableFuture2 = callProcessService.doAsync2(test);
        CompletableFuture<String> stringCompletableFuture3 = callProcessService.doAsync3(test);
        CompletableFuture.allOf(stringCompletableFuture1, stringCompletableFuture2, stringCompletableFuture3);

    }
}
