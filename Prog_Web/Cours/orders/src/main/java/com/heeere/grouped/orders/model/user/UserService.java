package com.heeere.grouped.orders.model.user;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * UserService
 */
@Component
public class UserService implements UserDetailsService {


    @Inject
    UserRepository repo;

    public final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opt = repo.findById(username);
        if (opt.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        User u = opt.get();
        return new org.springframework.security.core.userdetails.User(u.userName, u.derivedPassword, u.getRoles());
    }

    public User saveUserComputingDerivedPassword(User u, String rawPassword) {
        setComputingDerivedPassword(u, rawPassword);
        repo.save(u);
        return u;
    }

    public void setComputingDerivedPassword(User u, String rawPassword) {
        String codedPassword = encoder.encode(rawPassword);
        u.setDerivedPassword(codedPassword);
    }

    public void makeUserAdmin(String username) {
        User u = repo.findById(username).orElse(null);
        u.getRoles().add(UserRole.ADMIN);
        repo.save(u);
    }

    public List<User> listAllUsers() {
        return repo.findAllByOrderByUserName();
    }
    
}