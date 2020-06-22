package fpt.fbiz.fremote.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity(name = "users")
public class User extends BaseEntity implements UserDetails, Serializable {
    private static final long serialVersionUID = 4815877135015943617L;

    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String email;

    @Column
    @JsonProperty(value = "avatar_key")
    private String avatarKey;

    @Column
    private Date dob;

    @Column
    @JsonProperty(value = "full_name")
    private String fullName;

    @Column
    private String department;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> authorities;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private Set<Request> requests;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    Set<TaskUser> requestTasks;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    public boolean isAccountNonExpired() {
        return false;
    }

    @JsonIgnore
    public boolean isAccountNonLocked() {
        return false;
    }

    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @JsonIgnore
    public boolean isEnabled() {
        return false;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
