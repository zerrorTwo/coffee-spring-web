package com.nghia.coffee_spring_web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nghia.coffee_spring_web.domain.Role;
import com.nghia.coffee_spring_web.domain.User;
import com.nghia.coffee_spring_web.domain.dto.RegisterDTO;
import com.nghia.coffee_spring_web.repository.RoleRepository;
import com.nghia.coffee_spring_web.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> findAllUser() {
        return this.userRepository.findAll();
    }

    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }

    public User handleSaveAUser(User user) {
        User user2 = this.userRepository.save(user);
        return user2;
    }

    public User getUserById(long id) {
        User user = this.userRepository.findById(id);
        return user;
    }

    public void deleteAUser(long id) {
        this.userRepository.deleteById(id);
    }

    // Mapper Class, transfer object
    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }

    public boolean checkEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
