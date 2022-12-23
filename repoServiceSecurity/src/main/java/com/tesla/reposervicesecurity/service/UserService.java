package com.tesla.reposervicesecurity.service;


import com.tesla.model.User;
import com.tesla.reposervicesecurity.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public UserRepo userRepo;

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public Optional<User> findByToken(Integer token) {
        return userRepo.findByToken(token);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

}
