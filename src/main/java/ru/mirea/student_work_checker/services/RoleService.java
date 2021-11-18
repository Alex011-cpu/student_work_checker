package ru.mirea.student_work_checker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.student_work_checker.entities.Role;
import ru.mirea.student_work_checker.repositories.RoleRep;

@Service
public class RoleService {

    @Autowired
    private RoleRep roleRep;

    public Role findByName(String name) {
        return roleRep.findByName(name);
    }
}
