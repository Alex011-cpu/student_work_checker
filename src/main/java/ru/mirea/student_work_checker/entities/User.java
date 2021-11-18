package ru.mirea.student_work_checker.entities;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {

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
    @Transient
    private String grp;
    @Transient
    private String dcp;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
