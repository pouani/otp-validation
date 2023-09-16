package Icode.sendotp.entity;

import Icode.sendotp.utils.RoleType;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Role")
public class Role extends AbstractEntity{

    @Column(name = "libelle")
    @Enumerated(EnumType.STRING)
    private RoleType libelle;

}
