package com.br.devs.hosp.scheduling.service;

import com.br.devs.hosp.scheduling.controller.dto.input.UserCreateDTO;
import com.br.devs.hosp.scheduling.controller.dto.input.UserUpdateDTO;
import com.br.devs.hosp.scheduling.controller.dto.output.UserOutputDTO;
import com.br.devs.hosp.scheduling.entities.User;
import com.br.devs.hosp.scheduling.mapper.UserMapper;
import com.br.devs.hosp.scheduling.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public Page<UserOutputDTO> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::toDto);
    }

    @Transactional(readOnly = true)
    public UserOutputDTO getUserById(String userId) {
        return userMapper.toDto(findUserById(userId));
    }

    @Transactional
    public UserOutputDTO createUser(UserCreateDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    public UserOutputDTO updateUser(String userId, UserUpdateDTO userDTO) {
        User user = findUserById(userId);
        userMapper.copyProperties(userDTO, user);
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    public void deleteUser(String userId) {
        userRepository.delete(findUserById(userId));
    }

    protected User findUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}