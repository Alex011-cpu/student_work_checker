package ru.mirea.student_work_checker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.student_work_checker.entities.Teacher;
import ru.mirea.student_work_checker.repositories.TeacherRep;

@Service
public class TeacherService {

    @Autowired
    private TeacherRep teacherRep;

    public boolean saveTeacher(Teacher teacher) {
        teacherRep.save(teacher);
        return true;
    }

    public Teacher findByUserId(Long id) {
        return teacherRep.findTeacherByUserId(id);
    }
}

