package ru.mirea.student_work_checker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.student_work_checker.entities.User;


@Repository
public interface UserRep extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
