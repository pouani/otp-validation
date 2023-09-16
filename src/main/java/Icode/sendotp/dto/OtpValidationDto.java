package Icode.sendotp.dto;

import Icode.sendotp.entity.OtpValidation;
import Icode.sendotp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtpValidationDto {

        private Integer id;
        private Instant creationOtp;
        private Instant expireOtp;
        private Instant activationOtp;
        private String code;
        private UserDto user;

        public static OtpValidationDto toEntity(OtpValidationDto otpValidationDto) {
            if(otpValidationDto == null) {
                return null;
            }
            OtpValidation otpValidation = new OtpValidation();
            otpValidation.setId(otpValidation.getId());
            otpValidation.setCreationOtp(otpValidation.getCreationOtp());
            otpValidation.setExpireOtp(otpValidation.getExpireOtp());
            otpValidation.setActivationOtp(otpValidation.getActivationOtp());
            otpValidation.setCode(otpValidation.getCode());
            otpValidation.setUser(UserDto.toEntity(otpValidationDto.getUser()));

            return otpValidationDto;
        }

        public static OtpValidationDto fromEntity(OtpValidation otpValidation) {
            if(otpValidation == null){
                return null;
            }
            return OtpValidationDto.builder()
                    .id(otpValidation.getId())
                    .creationOtp(otpValidation.getCreationOtp())
                    .activationOtp(otpValidation.getActivationOtp())
                    .expireOtp(otpValidation.getExpireOtp())
                    .user(UserDto.fromEntity(otpValidation.getUser()))
                    .build();
        }

}
