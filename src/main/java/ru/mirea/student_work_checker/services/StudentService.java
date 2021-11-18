package ru.mirea.student_work_checker.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.student_work_checker.entities.Student;
import ru.mirea.student_work_checker.entities.User;
import ru.mirea.student_work_checker.repositories.StudentRep;

import java.util.List;
import java.util.Set;

@Service
public class StudentService {

    @Autowired
    private StudentRep studentRep;

    public boolean saveStudent(Student student) {
        studentRep.save(student);
        return true;
    }

    public Student findStudentById(Long id) {
        return studentRep.findStudentById(id);
    }

    public Student findStudentByUserId(Long id){
        return studentRep.findStudentByUserId(id);
    }
}
