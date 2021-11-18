package ru.mirea.student_work_checker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.mirea.student_work_checker.entities.Role;
import ru.mirea.student_work_checker.entities.User;
import ru.mirea.student_work_checker.repositories.UserRep;
import ru.mirea.student_work_checker.services.UserService;

import java.util.Collections;

@SpringBootApplication
public class StudentWorkCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentWorkCheckerApplication.class, args);
	}

}
