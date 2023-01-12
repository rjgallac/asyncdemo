package uk.co.sheffieldwebprogrammer.asyncdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;

import static uk.co.sheffieldwebprogrammer.asyncdemo.controller.Functions.getCompletedString;

@RestController
@Slf4j
public class CallProcess {

    public static final int ONE_SEC_SLEEP = 100;
    public static final int TWO_SECOND_SLEEP = 200;

    @Autowired
    private CallAllProcessService callAllProcessService;

    @GetMapping("test")
    public String test() {
        long start = System.currentTimeMillis();
        ArrayList<CompletableFuture<String>> futures = new ArrayList();
        for(int i = 0; i < 10; i++) {
            futures.add(callAllProcessService.callAll(Integer.toString(i + 1, 10), ONE_SEC_SLEEP, TWO_SECOND_SLEEP, ONE_SEC_SLEEP));
        }
        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
        return "test:<br />" + futures.stream().map(getCompletedString).collect(Collectors.joining(",<br />"));
    }

}
