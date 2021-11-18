package ru.mirea.student_work_checker.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.mirea.student_work_checker.entities.User;
import ru.mirea.student_work_checker.services.StudentService;
import ru.mirea.student_work_checker.services.UserService;

import java.security.Principal;

@Controller
public class StudentController {
    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;

    @GetMapping("/studentInfo")
    public String getStudentInfo(Model model, Principal principal){
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("student", studentService.findStudentByUserId(user.getId()));
        model.addAttribute("disciplineList",studentService.findStudentByUserId(user.getId()).getGroup().getDisciplines());
        return "person_student";
    }
}
