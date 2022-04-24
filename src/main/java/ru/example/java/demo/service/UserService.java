package ru.example.java.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.java.demo.model.user.AuthenticatedUser;
import ru.example.java.demo.model.user.Role;
import ru.example.java.demo.model.user.User;
import ru.example.java.demo.repository.UserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    @Transactional
    public void create(User user){

        userRepository.findByUsername(user.getUsername()).orElseThrow();
        user.setRole(Collections.singleton(Role.USER));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setActive(true);
        userRepository.save(user);

    }

    public void delete(Long id){userRepository.deleteById(id);}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AuthenticatedUser(userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found")));
    }
}
