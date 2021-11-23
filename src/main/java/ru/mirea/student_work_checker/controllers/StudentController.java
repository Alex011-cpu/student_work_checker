package ru.mirea.student_work_checker.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.student_work_checker.entities.Group;
import ru.mirea.student_work_checker.entities.Student;
import ru.mirea.student_work_checker.entities.StudentAnswer;
import ru.mirea.student_work_checker.entities.User;
import ru.mirea.student_work_checker.services.GroupService;
import ru.mirea.student_work_checker.services.StudentAnswerService;
import ru.mirea.student_work_checker.services.StudentService;
import ru.mirea.student_work_checker.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;

    @Autowired
    GroupService groupService;

    @Autowired
    StudentAnswerService studentAnswerService;

    @GetMapping("/studentInfo")
    public String getStudentInfo(Model model, Principal principal){
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("student", studentService.findStudentByUserId(user.getId()));
        model.addAttribute("disciplineList",studentService.findStudentByUserId(user.getId()).getGroup().getDisciplines());
        return "person_student";
    }

    @GetMapping("/student/getAllByGroup/{group}")
    @ResponseBody
    public List<Student> getAllByGroup(@PathVariable String group) {
        Group group1 = groupService.findGroupByName(group);
        List<Student> studentList = studentService.findAllByGroup(group1.getId());
        for (Student s: studentList) {
            System.out.println("Студент: " + s.getUser() + "\n"
                    + "Ответы: " + s.getAnswers() );
        }
        return studentList;
    }

    @GetMapping("/student/getAnswer/{student_id}/{task_id}")
    @ResponseBody
    public StudentAnswer getAnswerByStudentIdTaskId(@PathVariable Long student_id,
                                                       @PathVariable Long task_id) {
        return studentAnswerService.findByStudentAndTask(student_id, task_id);
    }

}
