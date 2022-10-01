package com.gameladder.gameladder.User;

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


    @Inject UserRepository repo; // On inject le repo User

    public final PasswordEncoder encoder = new BCryptPasswordEncoder(); // Cryptage de MDP

    // Fonction qui charge les données d'un Utilisateur
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opt = repo.findById(username);
        if (opt.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        User u = opt.get();
        return new org.springframework.security.core.userdetails.User(u.getUserName(), u.getDerivedPassword(), u.getRoles());
    }

    // Fonction qui enregistre le MDP crypté a un Utilisateur dans la DB
    public User saveUserComputingDerivedPassword(User u, String rawPassword) {
        setComputingDerivedPassword(u, rawPassword);
        repo.save(u);
        return u;
    }

    // Fonction qui enregistre le MDP crypté a un Utilisateur dans la Class
    public void setComputingDerivedPassword(User u, String rawPassword) {
        String codedPassword = encoder.encode(rawPassword);
        u.setDerivedPassword(codedPassword);
    }

    // Fonction qui met un Utilisateur en ADMIN
    public void makeUserAdmin(String username) {
        User u = repo.findById(username).orElse(null);
        u.getRoles().add(UserRole.ADMIN);
        repo.save(u);
    }

    // Fonction qui liste tous les Utilisateurs
    public List<User> listAllUsers() {
        return repo.findAllByOrderByUserName();
    }
    
}
