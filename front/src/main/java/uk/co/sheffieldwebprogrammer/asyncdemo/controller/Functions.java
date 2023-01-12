package uk.co.sheffieldwebprogrammer.asyncdemo.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class Functions {
    public static Function<CompletableFuture<String>, String> getCompletedString = x -> get(x);

    private static String get(CompletableFuture<String> test){
        try {
            return test.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
