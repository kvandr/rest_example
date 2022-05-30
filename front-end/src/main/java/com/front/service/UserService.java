package com.front.service;

import com.front.model.User;
import com.front.repos.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserClient userRepo;
    public UserService(UserClient userRepo) {
        this.userRepo = userRepo;
    }
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User loadUserByUsername(String username){
        return userRepo.findByUsername(username);
    }

    public List<User> readAll(){
        return userRepo.findAll();
    }

    public boolean delete(Long id){
        userRepo.deleteById(id);
        return true;
    }

    public void save(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }
}

