package ru.mirea.student_work_checker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.mirea.student_work_checker.controllers.DisciplineController;
import ru.mirea.student_work_checker.entities.Role;
import ru.mirea.student_work_checker.entities.User;
import ru.mirea.student_work_checker.repositories.UserRep;
import ru.mirea.student_work_checker.services.UserService;

import java.io.File;
import java.util.Collections;

@SpringBootApplication
/*@EnableConfigurationProperties(StorageProperties.class)*/
public class StudentWorkCheckerApplication {

	public static void main(String[] args) {
		new File(DisciplineController.uploadDirectory).mkdir();
		SpringApplication.run(StudentWorkCheckerApplication.class, args);
	}

}
