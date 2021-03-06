package ru.mirea.student_work_checker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "disciplines")
@EqualsAndHashCode(exclude={"tasks"})
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnoreProperties("disciplines")
    @OneToMany(mappedBy = "disciplines")
    List<Task> tasks;
}
