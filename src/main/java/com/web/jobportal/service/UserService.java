package com.web.jobportal.service;

import com.web.jobportal.entity.JobSeekerProfile;
import com.web.jobportal.entity.RecruiterProfile;
import com.web.jobportal.entity.User;
import com.web.jobportal.repository.JobSeekerProfileRepository;
import com.web.jobportal.repository.RecruiterProfileRepository;
import com.web.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RecruiterProfileRepository recruiterProfileRepository, JobSeekerProfileRepository jobSeekerProfileRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User addNew(User user) {
        user.setActive(true);
        user.setRegistrationDate(new Date(System.currentTimeMillis()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int userTypeId = user.getUserTypeId().getUserTypeId();
        User savedUser = userRepository.save(user);
        if(userTypeId == 1) {
            recruiterProfileRepository.save(new RecruiterProfile(user));
        } else {
            jobSeekerProfileRepository.save(new JobSeekerProfile(user));
        }
        return savedUser;
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Object getCurrentUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Couldn't find user with name: " + username));
            int userId = user.getUserId();
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))) {
                System.out.println("OVER HERE");
                return recruiterProfileRepository.findById(userId).orElse(new RecruiterProfile());
            } else {
                System.out.println("OVER HERE 2");
                return jobSeekerProfileRepository.findById(userId).orElse(new JobSeekerProfile());
            }
        }
        System.out.println("OVER HERE 3");
        return null;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Couldn't find user with name: " + username));
            return user;
        }
        return null;
    }
}
