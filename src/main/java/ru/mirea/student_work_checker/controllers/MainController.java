package ru.mirea.student_work_checker.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {
    @GetMapping("/")
    public String homePage() {
        return "Home";
    }

    @GetMapping("/contentForStudent")
    public String pageForStudent(Principal principal) {
        return "hello:  " + principal.getName();
    }

    @GetMapping("/contentForTeacher")
    public String pageForTeacher(Principal principal) {
        return "hello:  " + principal.getName();
    }

    @GetMapping("/contentForAdmin")
    public String pageForAdmin(Principal principal) {
        return "hello:  " + principal.getName();
    }
}
