package com.br.devs.hosp.scheduling.service;

import com.br.devs.hosp.scheduling.controller.dto.UserDTO;
import com.br.devs.hosp.scheduling.entities.User;
import com.br.devs.hosp.scheduling.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.name());
        user.setMail(userDTO.mail());
        user.setUserType(userDTO.userType());
        user.setExpertise(userDTO.expertise());
        return userRepository.save(user);
    }

    public User updateUser(String userId, UserDTO userDTO) {
        User user = getUserById(userId);
        user.setName(userDTO.name());
        user.setMail(userDTO.mail());
        user.setUserType(userDTO.userType());
        user.setExpertise(userDTO.expertise());
        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        User user = getUserById(userId);
        userRepository.delete(user);
    }
}
