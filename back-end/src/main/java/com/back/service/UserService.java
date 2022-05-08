package com.back.service;

import com.back.model.User;

import java.util.List;

public interface UserService {
    User create(User user);

    List<User> readAll();

    User read(Long id);

    boolean update(User user, Long id);

    boolean delete(Long id);

    User findByUsername(String username);

    User findUserById(Long id);

    List<User> findAll();

    void deleteById(Long id);
}
