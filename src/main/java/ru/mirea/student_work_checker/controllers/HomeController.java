package ru.mirea.student_work_checker.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.mirea.student_work_checker.entities.Role;
import ru.mirea.student_work_checker.entities.Teacher;
import ru.mirea.student_work_checker.entities.User;
import ru.mirea.student_work_checker.services.StudentService;
import ru.mirea.student_work_checker.services.TeacherService;
import ru.mirea.student_work_checker.services.UserService;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @GetMapping("/")
    public String homePage(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("userObj", user);
        if (user.getRoles().contains(new Role(1L,"ROLE_STUDENT"))) {
            model.addAttribute("disciplineList",studentService.findStudentByUserId(user.getId()).getGroup().getDisciplines());
            return "index";
        } else {
            model.addAttribute("disciplineList",teacherService.findByUserId(user.getId()).getDiscipline());
            return "teacher_site";
        }
    }
}
