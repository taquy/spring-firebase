package fpt.fbiz.fremote.entities;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@NoArgsConstructor
@Entity(name = "roles")
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Override
    public String getAuthority() {
        return "Anonymous";
    }
}
