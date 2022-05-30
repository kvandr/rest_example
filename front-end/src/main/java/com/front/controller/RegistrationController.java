package com.front.controller;

import com.front.model.Role;
import com.front.model.User;
import com.front.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/registration")
    public String registration(Map<String, Object> model){

        model.put("users",userService.readAll());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String addUser(@RequestParam(name="username") String username,
                          @RequestParam(name="password") String password,
                          @RequestParam(name="roles") String role,
                          Map<String, Object> model){
        if (userService.loadUserByUsername(username)!=null){
            model.put("message", "User exists!");
            return "registration";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if (Objects.equals(role, "USER")){
            user.setRoles(Collections.singleton(Role.USER));
        }
        else{
            user.setRoles(Collections.singleton(Role.ADMIN));
        }
        user.setActive(true);
        userService.save(user);
        return "redirect:/registration";
    }
    @GetMapping(value = "/registration/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id, Map<String, Object> model) {
        model.put("users", userService.delete(id));
        return "redirect:/registration";
    }
}
