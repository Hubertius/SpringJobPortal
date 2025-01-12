package com.web.jobportal.controller;

import com.web.jobportal.entity.RecruiterProfile;
import com.web.jobportal.entity.User;
import com.web.jobportal.service.RecruiterProfileService;
import com.web.jobportal.service.UserService;
import com.web.jobportal.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("recruiter-profile")
public class RecruiterProfileController {

    private final UserService userService;
    private final RecruiterProfileService recruiterProfileService;

    @Autowired
    public RecruiterProfileController(UserService userService, RecruiterProfileService recruiterProfileService) {
        this.userService = userService;
        this.recruiterProfileService = recruiterProfileService;
    }

    @GetMapping("/")
    public String recruiterProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            User user = userService.getUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Couldn't find the user with the name: " + username));
            Optional<RecruiterProfile> recruiterProfile = recruiterProfileService.getProfileById(user.getUserId());
            if(recruiterProfile.isPresent()) {
                model.addAttribute("profile", recruiterProfile);
            }
        }
        return "recruiter_profile";
    }

    @PostMapping("/addNew")
    public String addNew(RecruiterProfile recruiterProfile, @RequestParam("image") MultipartFile multipartFile, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            User user = userService.getUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Couldn't find the user with the name: " + username));
            recruiterProfile.setUserId(user);
            recruiterProfile.setUserAccountId(user.getUserId());
        }
        model.addAttribute("profile", recruiterProfile);
        String filename = "";
        if(!multipartFile.getOriginalFilename().equals("")) {
            filename = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            recruiterProfile.setProfilePhoto(filename);
        }
        RecruiterProfile savedProfile = recruiterProfileService.addNew(recruiterProfile);

        String uploadDir = "photos/recruiter" + savedProfile.getUserAccountId();
        try {
            FileUploadUtil.saveFile(uploadDir, filename, multipartFile);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "redirect:/dashboard";
    }
}
