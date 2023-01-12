package uk.co.sheffieldwebprogrammer.asyncdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class CallProcess {

    @Autowired
    private CallProcessService callProcessService;

    @GetMapping("test")
    public String test() throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();

        Predicate<String> not5 = x -> !x.equals("test2");
        Function<CompletableFuture<String>, String> getCompletedString = x -> get(x);
        Integer nums[] = {1,2,3};
        List<CompletableFuture<String>> completableFutures1 = Arrays.stream(nums)
                .map(x -> callProcessService.doAsync1("test" + x))
                .collect(Collectors.toList());
        List<CompletableFuture<String>> completableFutures2 = Arrays.stream(nums)
                .map(x -> callProcessService.doAsync2("test" + x))
                .collect(Collectors.toList());
        List<CompletableFuture<String>> completableFutures3 = Arrays.stream(nums)
                .map(x -> callProcessService.doAsync3("test" + x))
                .collect(Collectors.toList());

        List<CompletableFuture<String>> completableFutures = Stream.of(completableFutures1, completableFutures2, completableFutures3)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).join();

        String collect = completableFutures.stream().map(getCompletedString).filter(not5).collect(Collectors.joining(","));
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
        return "test:" + collect;
    }

    private String get(CompletableFuture<String> test){
        try {
            return test.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
