package ru.mirea.student_work_checker.entities;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^(.+)@(.+)$", message = "Неверный адрес электронной почты")
    private String email;
    @Size(min = 4, message = "Введите не меньше 3 знаков")
    private String firstName;
    @Size(min = 4, message = "Введите не меньше 3 знаков")
    private String lastName;
    @Size(min = 4, message = "Пароль должен содержать не меньше 4 знаков")
    private String password;
    @Transient
    private String passwordConfirm;
    @Transient
    private String role;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;
}
