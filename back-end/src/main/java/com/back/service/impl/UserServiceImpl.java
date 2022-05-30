package com.back.service.impl;

import com.back.model.Role;
import com.back.model.User;
import com.back.repos.UserRepo;
import com.back.service.UserService;
import javassist.bytecode.SignatureAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public User create(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> readAll() {
        return userRepo.findAll();
    }

    @Override
    public User read(Long id) {
        return userRepo.findUserById(id);
    }

    @Override
    public boolean update(User user, Long id) {
        userRepo.save(user);
        return true;

    }

    @Override
    public boolean delete(Long id) {
        userRepo.delete(userRepo.findUserById(id));
        return true;
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User findUserById(Long id) {
        return userRepo.findUserById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

}
