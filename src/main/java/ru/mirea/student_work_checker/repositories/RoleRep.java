package ru.mirea.student_work_checker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.student_work_checker.entities.Role;

@Repository
public interface RoleRep extends JpaRepository<Role, Long> {
}
