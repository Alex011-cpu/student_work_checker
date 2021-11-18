package ru.mirea.student_work_checker.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mirea.student_work_checker.entities.Role;
import ru.mirea.student_work_checker.entities.Student;
import ru.mirea.student_work_checker.entities.Teacher;
import ru.mirea.student_work_checker.entities.User;
import ru.mirea.student_work_checker.repositories.UserRep;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRep userRep;



    @Autowired
    private RoleService roleService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private DisciplineService disciplineService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findByEmail(String email) {
        return userRep.findByEmail(email);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", email));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public Boolean saveUser(User user) {
        User userDb = userRep.findByEmail(user.getEmail());

        if (userDb != null) {
            return false;
        }
        user.setRoles(Collections.singleton(roleService.findByName("ROLE_"+user.getRole())));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRep.save(user);
        if (user.getRole().equals("STUDENT")) {
            Student student = new Student();
            student.setUser(user);
            student.setGroup(groupService.findGroupByName(user.getGrp()));
            studentService.saveStudent(student);
        } else if (user.getRole().equals("TEACHER")) {
            Teacher teacher = new Teacher();
            teacher.setUser(user);
            teacher.setDiscipline(disciplineService.findDisciplineByName(user.getDcp()));
            teacherService.saveTeacher(teacher);
        }
        return true;
    }
}

