package com.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.model.Email;
import com.training.repo.EmailRepo;
import com.training.repo.PropertyRepo;
import com.training.repo.UserRepo;

@Service
public class EmailService {

    @Autowired
    private UserService userService;

    @Autowired
    private PropertyRepo propRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailRepo emailRepo;

    public void send(int pid, int user_id) {
        var propertyOpt = propRepo.findById(pid);
        var userOpt = userRepo.findById(user_id);

        if (propertyOpt.isPresent() && userOpt.isPresent()) {
            String propertyname = propertyOpt.get().getName();
            String username = userOpt.get().getName();
            String message = propertyname + " booking requested by " + username;

            Email email = new Email();
            email.setEmailBody(message);
            email.setOwner_id(propertyOpt.get().getOwner_id());
            email.setPid(pid);
            email.setUser_id(user_id);
            email.setUseremail(userOpt.get().getEmail());

            emailRepo.save(email);
        }
        // Consider adding exception handling for missing data
    }

    public List<Email> myEmail(int owner_id) {
        return emailRepo.findByOwner_id(owner_id);
    }
}
