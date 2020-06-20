package fpt.fbiz.fremote.services;

import fpt.fbiz.fremote.dtos.AuthSignUpDto;
import fpt.fbiz.fremote.entities.Role;
import fpt.fbiz.fremote.entities.User;
import fpt.fbiz.fremote.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService extends BaseService<User, UserRepository> implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository) {
        super(repository);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDetails userDetails = userRepository.findByUsername(username);
        UserDetails userDetails = null;
//        if (userDetails == null)
//            return null;

        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        for (GrantedAuthority role : userDetails.getAuthorities()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }

        return new org.springframework.security.core.userdetails.User(userDetails.getUsername(),
                userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Transactional
    public User register(
            AuthSignUpDto dto
    ) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setAuthorities(List.of(new Role()));

        var rawPassword = dto.getPassword();
        var encryptedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encryptedPassword);
        return user;
    }
}
