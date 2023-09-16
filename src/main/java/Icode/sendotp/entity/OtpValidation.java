package Icode.sendotp.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "OtpValidation")
public class OtpValidation extends AbstractEntity{

    @Column(name = "creationOtp")
    private Instant creationOtp;

    @Column(name = "expireOtp")
    private Instant expireOtp;

    @Column(name = "activationOtp")
    private Instant activationOtp;

    @Column(name = "code")
    private String code;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

}
