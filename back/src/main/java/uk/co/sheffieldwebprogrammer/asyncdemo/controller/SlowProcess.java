package uk.co.sheffieldwebprogrammer.asyncdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SlowProcess {

    @GetMapping("/test")
    public String getSlow1(@RequestParam(name="test") String test, @RequestParam(name="sleep") long sleep){
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Elapsed time back: " + (System.currentTimeMillis() - start));

        return test;
    }
}
