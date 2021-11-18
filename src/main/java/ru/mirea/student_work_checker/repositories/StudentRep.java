package ru.mirea.student_work_checker.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.student_work_checker.entities.Student;

@Repository
public interface StudentRep extends JpaRepository<Student, Long> {

        Student findStudentById(Long id);
        Student findStudentByUserId(Long id);
}
