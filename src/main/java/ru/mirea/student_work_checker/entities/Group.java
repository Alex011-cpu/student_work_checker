package ru.mirea.student_work_checker.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "discipline_id")
    Set<Discipline> disciplines;
}
