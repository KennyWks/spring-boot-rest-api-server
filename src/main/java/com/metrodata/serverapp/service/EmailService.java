package com.metrodata.serverapp.service;

import com.metrodata.serverapp.model.request.Email;

public interface EmailService {

     Email sendSimpleEmail(Email email);
     Email sendEmailWithAttachment(Email email);
}
