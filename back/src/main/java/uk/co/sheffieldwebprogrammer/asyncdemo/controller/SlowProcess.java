package uk.co.sheffieldwebprogrammer.asyncdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlowProcess {

    @GetMapping("/test1")
    public String getSlow1(@RequestParam String test){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("1");
        return test;
    }

    @GetMapping("/test2")
    public String getSlow2(@RequestParam String test){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("2");
        return test;
    }

    @GetMapping("/test3")
    public String getSlow3(@RequestParam String test){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("3");
        return test;
    }
}
