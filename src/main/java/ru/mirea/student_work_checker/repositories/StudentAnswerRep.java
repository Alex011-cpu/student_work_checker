package ru.mirea.student_work_checker.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mirea.student_work_checker.entities.Student;
import ru.mirea.student_work_checker.entities.StudentAnswer;
import ru.mirea.student_work_checker.entities.Task;

@Repository
public interface StudentAnswerRep extends JpaRepository<StudentAnswer, Long> {

    StudentAnswer findByStudentIdAndTaskId(Long student_id,Long task_id);

    StudentAnswer findStudentAnswerById(Long id);
}
