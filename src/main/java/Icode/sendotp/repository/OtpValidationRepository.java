package Icode.sendotp.repository;

import Icode.sendotp.entity.OtpValidation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OtpValidationRepository extends CrudRepository<OtpValidation, Integer> {
    Optional<OtpValidation> findByCode(String code);
}
