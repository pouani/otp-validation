package Icode.sendotp.services;

import Icode.sendotp.dto.UserDto;
import Icode.sendotp.entity.OtpValidation;
import Icode.sendotp.entity.User;
import Icode.sendotp.repository.OtpValidationRepository;
import Icode.sendotp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Slf4j
@Service
public class OtpValidationService {

    private OtpValidationRepository validationRepository;
    private NotificationService notificationService;

    private UserRepository userRepository;

    @Autowired
    public OtpValidationService(
            OtpValidationRepository validationRepository,
            NotificationService notificationService,
            UserRepository userRepository
    ) {
        this.validationRepository = validationRepository;
        this.notificationService = notificationService;
        this.userRepository = userRepository;
    }

    public void save(UserDto dto){

        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        OtpValidation validation = new OtpValidation();
        validation.setUser(user);
        Instant creation = Instant.now();
        validation.setCreationOtp(creation);
        Instant expiration = creation.plus(3, ChronoUnit.MINUTES);
        validation.setExpireOtp(expiration);

        Random random = new Random();
        int rdmInteger = random.nextInt(999999);
        String code = String.format("%06d", rdmInteger);
        validation.setCode(code);

        this.validationRepository.save(validation);
        this.notificationService.send(validation);
    }

    public OtpValidation readCode(String code){
        if(code == null){
            log.error("code is null");
            return null;
        }

        return this.validationRepository.findByCode(code).orElseThrow(
                    () -> new RuntimeException("Your code is invalid")
                );
    }
}
