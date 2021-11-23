package ru.mirea.student_work_checker.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.student_work_checker.entities.Student;
import ru.mirea.student_work_checker.entities.StudentAnswer;
import ru.mirea.student_work_checker.entities.Task;
import ru.mirea.student_work_checker.repositories.StudentAnswerRep;

@Service
public class StudentAnswerService {

    @Autowired
    StudentAnswerRep studentAnswerRep;

    public boolean saveStudentAnswer(StudentAnswer studentAnswer) {
        studentAnswerRep.save(studentAnswer);
        return true;
    }

    public StudentAnswer findByStudentAndTask(Long student_id, Long task_id) {
        return studentAnswerRep.findByStudentIdAndTaskId(student_id, task_id);
    }

    public StudentAnswer findById(Long id) {
        return studentAnswerRep.findStudentAnswerById(id);
    }
}
