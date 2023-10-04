package com.metrodata.serverapp.controller;

import com.metrodata.serverapp.model.request.Email;
import com.metrodata.serverapp.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@AllArgsConstructor
public class EmailController {

    private EmailService emailService;

    @PostMapping
    public ResponseEntity<Email> simpleEmail(@RequestBody Email email){
        return new ResponseEntity<>(emailService.sendSimpleEmail(email), HttpStatus.OK);
    }

    @PostMapping("/attach")
    public ResponseEntity<Email> emailWithAttachment(@RequestBody Email email){
        return new ResponseEntity<>(emailService.sendEmailWithAttachment(email), HttpStatus.OK);
    }

}
