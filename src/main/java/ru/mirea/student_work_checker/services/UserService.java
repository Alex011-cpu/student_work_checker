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

        user.setRoles(Collections.singleton(new Role("ROLE_" + user.getRole())));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRep.save(user);
        return true;
    }
}

