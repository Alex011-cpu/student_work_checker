package ru.mirea.student_work_checker.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.student_work_checker.entities.Group;
import ru.mirea.student_work_checker.repositories.GroupRep;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    GroupRep groupRep;

    public Group findGroupByName(String name) {
        return groupRep.findGroupByName(name);
    }

    public List<Group> findAll() {
        return groupRep.findAll();
    }
}
