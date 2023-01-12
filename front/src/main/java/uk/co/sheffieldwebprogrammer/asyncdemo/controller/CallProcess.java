package uk.co.sheffieldwebprogrammer.asyncdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
public class CallProcess {

    @Autowired
    private CallProcessService callProcessService;

    @Autowired
    private CallAllProcessService callAllProcessService;

    @GetMapping("test")
    public String test() throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();

        CompletableFuture<String> stringCompletableFuture1 = callAllProcessService.callAll("1", 1000, 1000, 1000);
        CompletableFuture<String> stringCompletableFuture2 = callAllProcessService.callAll("2", 1000, 1000, 1000);
        CompletableFuture<String> stringCompletableFuture3 = callAllProcessService.callAll("3", 1000, 1000, 1000);
        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
        CompletableFuture.allOf(stringCompletableFuture1, stringCompletableFuture2, stringCompletableFuture3).join();

        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
        return "test:" + stringCompletableFuture1.get() + stringCompletableFuture2.get() + stringCompletableFuture3.get();
    }
}
