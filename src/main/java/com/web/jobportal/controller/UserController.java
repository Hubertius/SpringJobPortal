package com.web.jobportal.controller;

import com.web.jobportal.entity.User;
import com.web.jobportal.entity.UserType;
import com.web.jobportal.service.UserService;
import com.web.jobportal.service.UserTypeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final UserTypeService userTypeService;

    @Autowired
    public UserController(UserService userService, UserTypeService userTypeService) {
        this.userService = userService;
        this.userTypeService = userTypeService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        List<UserType> userTypes = userTypeService.getAll();
        model.addAttribute("getAllTypes", userTypes);
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register/new")
    public String userRegistration(User user, Model model) {
        if(userService.getUserByEmail(user.getEmail()).isEmpty()) {
            userService.addNew(user);
            return "dashboard";
        } else {
            List<UserType> userTypes = userTypeService.getAll();
            model.addAttribute("getAllTypes", userTypes);
            model.addAttribute("user", new User());
            model.addAttribute("error", "Email already present within the database!");
            return "register";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
        }
        return "redirect:/";
    }
}
