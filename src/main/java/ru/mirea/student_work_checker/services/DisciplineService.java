package ru.mirea.student_work_checker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.student_work_checker.entities.Discipline;
import ru.mirea.student_work_checker.repositories.DisciplineRep;

import java.util.List;

@Service
public class DisciplineService {

    @Autowired
    private DisciplineRep disciplineRep;

    public List<Discipline> findAll() {
        return disciplineRep.findAll();
    }

    public Discipline findDisciplineByName(String name) {
        return disciplineRep.findDisciplineByName(name);
    }
}
