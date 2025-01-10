package com.web.jobportal.service;

import com.web.jobportal.entity.JobSeekerProfile;
import com.web.jobportal.entity.RecruiterProfile;
import com.web.jobportal.entity.User;
import com.web.jobportal.repository.JobSeekerProfileRepository;
import com.web.jobportal.repository.RecruiterProfileRepository;
import com.web.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private RecruiterProfileRepository recruiterProfileRepository;
    private JobSeekerProfileRepository jobSeekerProfileRepository;

    @Autowired
    public UserService(UserRepository userRepository, RecruiterProfileRepository recruiterProfileRepository, JobSeekerProfileRepository jobSeekerProfileRepository) {
        this.userRepository = userRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
    }

    public User addNew(User user) {
        user.setActive(true);
        user.setRegistrationDate(new Date(System.currentTimeMillis()));
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
}
