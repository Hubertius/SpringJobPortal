package com.web.jobportal.controller;

import com.web.jobportal.entity.User;
import com.web.jobportal.entity.UserType;
import com.web.jobportal.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserTypeService userTypeService;

    @Autowired
    public UserController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        List<UserType> userTypes = userTypeService.getAll();
        model.addAttribute("allUserTypes", userTypes);
        model.addAttribute("user", new User());
        return "register";
    }
}
