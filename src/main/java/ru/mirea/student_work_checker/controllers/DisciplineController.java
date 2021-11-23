package ru.mirea.student_work_checker.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mirea.student_work_checker.entities.*;
import ru.mirea.student_work_checker.services.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
public class DisciplineController {


    public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/files";

    @Autowired
    UserService userService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Autowired
    DisciplineService disciplineService;

    @Autowired
    TaskService taskService;

    @Autowired
    GroupService groupService;

    @Autowired
    StudentAnswerService studentAnswerService;

    @GetMapping("/discipline/{discipline_name}")
    public String getDisciplinePage(@PathVariable String discipline_name, Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        Discipline discipline = disciplineService.findDisciplineByName(discipline_name);
        model.addAttribute("discipline", discipline);
        model.addAttribute("taskList",discipline.getTasks());
        if (user.getRoles().contains(new Role(2L,"ROLE_TEACHER"))) {
            Teacher teacher = teacherService.findByUserId(user.getId());
            model.addAttribute("teacher", teacher);
            return "teacher_discipline";
        } else {
            Student student = studentService.findStudentByUserId(user.getId());
            model.addAttribute("student" , student);
            return "student_discipline";
        }
    }

    @GetMapping("/discipline/{discipline_name}/addTask")
    public String addTaskPage(@PathVariable String discipline_name, Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        Teacher teacher = teacherService.findByUserId(user.getId());
        model.addAttribute("teacher", teacher);
        model.addAttribute("discipline", disciplineService.findDisciplineByName(discipline_name));
        return "addTask";
    }

    @PostMapping("/discipline/{discipline_name}/addTask")
    public String addTaskPage(@ModelAttribute("taskForm") Task taskForm, @PathVariable String discipline_name,
                              @RequestParam("files")MultipartFile[] files, Principal principal) throws IOException {
        String pathToTeacherFiles="";
        StringBuilder filenames = new StringBuilder();
        for (MultipartFile file:files) {
            Path fileNameAndPath = Paths.get(uploadDirectory, " by " + principal.getName() + " " + file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
            pathToTeacherFiles=pathToTeacherFiles + fileNameAndPath.getFileName() + ";";
        }
        taskForm.setPathToFileTeacher(pathToTeacherFiles);
        taskForm.setDisciplines(disciplineService.findDisciplineByName(discipline_name));
        taskService.saveTask(taskForm);
        System.out.println(discipline_name);
        return "redirect:/";
    }

    @GetMapping("/discipline/{discipline_name}/task/{task_name}")
    public String addTaskPage(@PathVariable String discipline_name,@PathVariable String task_name ,Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        Task task = taskService.findTaskByName(task_name);
        model.addAttribute("discipline", disciplineService.findDisciplineByName(discipline_name));
        model.addAttribute("task", task);
        String[] filePaths = task.getPathToFileTeacher().split(";");
        model.addAttribute("fileList",filePaths);

        if (user.getRoles().contains(new Role(2L,"ROLE_TEACHER"))) {
            Teacher teacher = teacherService.findByUserId(user.getId());
            model.addAttribute("teacher", teacher);
            model.addAttribute("listGroups", groupService.findAll());
            model.addAttribute("answer",new StudentAnswer());
            return "teacher_task";
        } else {
            Student student = studentService.findStudentByUserId(user.getId());
            model.addAttribute("student", student);
            StudentAnswer sA = studentAnswerService.findByStudentAndTask(student.getId(),task.getId());
            if (sA!=null) {
                model.addAttribute("answerFiles", sA.getPathToFileStudent().split(";"));
                model.addAttribute("score", sA.getScore());
            }
            return "student_task";
        }

    }

    @PostMapping("/discipline/{discipline_name}/task/{task_name}")
    public String addAnswerByStudent(@PathVariable String discipline_name,@PathVariable String task_name , @RequestParam("files")MultipartFile[] files,Principal principal) throws IOException {
        String pathToStudentFiles="";
        Task taskFromDb = taskService.findTaskByName(task_name);
        StringBuilder filenames = new StringBuilder();
        for (MultipartFile file:files) {
            Path fileNameAndPath = Paths.get(uploadDirectory, " by " + principal.getName() + " " + file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
            pathToStudentFiles=pathToStudentFiles + fileNameAndPath.getFileName() + ";";
        }
        User user = userService.findByEmail(principal.getName());
        Student student = studentService.findStudentByUserId(user.getId());;
        StudentAnswer studentAnswer = new StudentAnswer();
        studentAnswer.setStudent(student);
        studentAnswer.setPathToFileStudent(pathToStudentFiles);
        studentAnswer.setTask(taskFromDb);
        studentAnswerService.saveStudentAnswer(studentAnswer);
        return "redirect:/";
    }
}
