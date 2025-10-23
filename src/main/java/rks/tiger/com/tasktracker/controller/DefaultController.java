package rks.tiger.com.tasktracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class DefaultController {

    @GetMapping("")
    public String DefaultController() {
       return "Welcome to TaskTracker!";
    }
}
