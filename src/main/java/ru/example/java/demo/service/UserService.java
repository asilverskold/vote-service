package ru.example.java.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.java.demo.model.User;
import ru.example.java.demo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(User user){return userRepository.save(user);}

    public void delete(Long id){userRepository.deleteById(id);}







}
