package Icode.sendotp.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "User")
public class User extends AbstractEntity {

    @Column(name = "userName")
    private String userName;

    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "userPhone")
    private String userPhone;

    @Column(name = "userPassword")
    private String userPassword;

    @Column(name = "enabled")
    private boolean enabled = false;

    @OneToOne(cascade = CascadeType.ALL)
    private Role role;

//    @Override
//    public Collection<? extends GrantedAuthority> geAuthorities() {
//        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+this.role.getLibelle()));
//    }

}
