package ru.mirea.student_work_checker.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "disciplines")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
