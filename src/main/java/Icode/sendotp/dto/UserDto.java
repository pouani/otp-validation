package Icode.sendotp.dto;

import Icode.sendotp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer id;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userPassword;

    public static UserDto fromEntity(User user){
        if(user == null){
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .userEmail(user.getUserEmail())
                .userPhone(user.getUserPhone())
                .userPassword(user.getUserPassword())
                .build();
    }

    public static User toEntity(UserDto userDto){
        if(userDto == null){
            return null;
        }

        User user = new User();
        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        user.setUserEmail(userDto.getUserEmail());
        user.setUserPhone(userDto.getUserPhone());
        user.setUserPassword(userDto.getUserPassword());

        return user;
    }

}
