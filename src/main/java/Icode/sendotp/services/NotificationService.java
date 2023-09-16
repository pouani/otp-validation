package Icode.sendotp.services;

import Icode.sendotp.dto.OtpValidationDto;
import Icode.sendotp.entity.OtpValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {

    JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(OtpValidation validation){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("no-reply@pouani.dev");
        mailMessage.setTo(validation.getUser().getUserEmail());
        mailMessage.setSubject("Your activation code ");

        String msg = String.format(
                "Hello %s, <br /> your activation code is %s",
                validation.getUser().getUserName(),
                validation.getCode()
        );

        mailMessage.setText(msg);
        javaMailSender.send(mailMessage);
    }

}
