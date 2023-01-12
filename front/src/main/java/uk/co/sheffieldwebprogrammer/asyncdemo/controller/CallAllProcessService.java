package uk.co.sheffieldwebprogrammer.asyncdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static uk.co.sheffieldwebprogrammer.asyncdemo.controller.Functions.getCompletedString;

@Service
@Slf4j
public class CallAllProcessService {

    @Autowired
    private CallProcessService callProcessService;

    @Async
    public CompletableFuture<String> callAll(String test, long firstSleep, long secondSleep, long thirdSleep){
        long start = System.currentTimeMillis();
        Predicate<String> not5 = x -> !x.equals("test2");

        CompletableFuture<String> stringCompletableFuture1 = callProcessService.doAsync("slow" + test, firstSleep);
        CompletableFuture<String> stringCompletableFuture2 = callProcessService.doAsync("medium" + test, secondSleep);
        CompletableFuture<String> stringCompletableFuture3 = callProcessService.doAsync("fast" + test, thirdSleep);
        List<CompletableFuture<String>> completableFutures = Arrays.asList(stringCompletableFuture1, stringCompletableFuture2, stringCompletableFuture3);
        CompletableFuture.allOf(stringCompletableFuture1, stringCompletableFuture2, stringCompletableFuture3).join();

        String collect = completableFutures.stream().map(getCompletedString).filter(not5).collect(Collectors.joining(","));
        log.info("Elapsed time sub: " + (System.currentTimeMillis() - start));

        return CompletableFuture.completedFuture(collect);

    }

}
