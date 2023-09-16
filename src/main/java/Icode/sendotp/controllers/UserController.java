package Icode.sendotp.controllers;

import Icode.sendotp.dto.UserDto;
import Icode.sendotp.entity.User;
import Icode.sendotp.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "register")
    public void register(@RequestBody UserDto userDto){
        log.info("Success register");
        this.userService.register(userDto);
    }

    @PostMapping(path = "code-activation")
    public void activation(@RequestBody Map<String, String> activation){
        log.info("Success register");
        this.userService.activation(activation);
    }

}
