package ru.mirea.student_work_checker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.student_work_checker.entities.Group;

import java.util.List;

@Repository
public interface GroupRep extends JpaRepository<Group, Long> {
    Group findGroupByName(String name);
    List<Group> findAll();
}
