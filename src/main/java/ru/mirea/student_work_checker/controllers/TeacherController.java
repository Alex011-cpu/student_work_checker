package ru.mirea.student_work_checker.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.student_work_checker.entities.StudentAnswer;
import ru.mirea.student_work_checker.entities.Teacher;
import ru.mirea.student_work_checker.entities.User;
import ru.mirea.student_work_checker.services.StudentAnswerService;
import ru.mirea.student_work_checker.services.StudentService;
import ru.mirea.student_work_checker.services.TeacherService;
import ru.mirea.student_work_checker.services.UserService;

import java.security.Principal;

@Controller
public class TeacherController {

    @Autowired
    UserService userService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentAnswerService studentAnswerService;

    @GetMapping("/teacherInfo")
    public String getStudentInfo(Model model, Principal principal){
        User user = userService.findByEmail(principal.getName());
        Teacher teacher = teacherService.findByUserId(user.getId());
        model.addAttribute("teacher", teacher);
        model.addAttribute("disciplineList",teacher.getDiscipline());
        return "person_teacher";
    }
    @ResponseBody
    @PostMapping("/teacher/addScore")
    public void getStudentInfo(@RequestBody StudentAnswer stA){
        StudentAnswer studentAnswer = studentAnswerService.findById(stA.getId());
        studentAnswer.setScore(stA.getScore());
        studentAnswerService.saveStudentAnswer(studentAnswer);
    }
}
