package Icode.sendotp.services;

import Icode.sendotp.dto.UserDto;
import Icode.sendotp.entity.OtpValidation;
import Icode.sendotp.entity.Role;
import Icode.sendotp.entity.User;
import Icode.sendotp.repository.UserRepository;
import Icode.sendotp.utils.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private UserRepository userRepository;

    private OtpValidationService validationService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder,
            OtpValidationService validationService
    ){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.validationService = validationService;
    }

    public UserDto register(UserDto userDto){
        if(!userDto.getUserEmail().contains("@")){
            log.error("Invalid mail");
            throw new RuntimeException("Invalid email adress");
        }
        Optional<User> userOptional = this.userRepository.findUserByUserEmail(userDto.getUserEmail());

        if(userOptional.isPresent()){
            throw new RuntimeException("Email already exists");
        }

        String pwdCrypt = this.passwordEncoder.encode(userDto.getUserPassword());
        userDto.setUserPassword(pwdCrypt);

        Role userRole = new Role();
        userRole.setLibelle(RoleType.USER);


        userDto = UserDto.fromEntity(userRepository.save(UserDto.toEntity(userDto)));
        this.validationService.save(userDto);

        return userDto;
    }

    public void activation(Map<String, String> activation) {
        OtpValidation validation = this.validationService.readCode(activation.get("code"));

        if(Instant.now().isAfter(validation.getExpireOtp())){
            throw new RuntimeException("Your code has expiered");
        }

        User userActivate = this.userRepository.findById(validation.getUser().getId())
                .orElseThrow(() -> new RuntimeException("unknown user"));

        userActivate.setEnabled(true);
        this.userRepository.save(userActivate);
    }
}
