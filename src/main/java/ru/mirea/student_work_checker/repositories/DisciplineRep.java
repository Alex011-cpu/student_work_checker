package ru.mirea.student_work_checker.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.student_work_checker.entities.Discipline;

import java.util.List;

@Repository
public interface DisciplineRep extends JpaRepository<Discipline,Long> {

    List<Discipline> findAll();
    Discipline findDisciplineByName(String name);
}
