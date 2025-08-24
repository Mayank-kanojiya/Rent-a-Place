package com.training.controller;

import com.training.Dto.ServerResponse;
import com.training.model.Email;
import com.training.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/owner/{owner_id}")
    public ResponseEntity<List<Email>> getEmails(@PathVariable("owner_id") int owner_id) {
        List<Email> emails = emailService.myEmail(owner_id);
        return ResponseEntity.ok(emails);
    }

    @PostMapping("/owner/{pid}/{user_id}")
    public ResponseEntity<ServerResponse> send(@PathVariable("pid") int pid, @PathVariable("user_id") int user_id) {
        emailService.send(pid, user_id);
        ServerResponse response = new ServerResponse();
        response.setMessage("Email Sent");
        response.setStatus(org.springframework.http.HttpStatus.OK);
        return ResponseEntity.ok(response);
    }
}
