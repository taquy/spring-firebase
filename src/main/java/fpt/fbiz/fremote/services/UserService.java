package fpt.fbiz.fremote.services;

import com.auth0.jwt.JWT;
import fpt.fbiz.fremote.consts.SecurityConstant;
import fpt.fbiz.fremote.dtos.AuthResult;
import fpt.fbiz.fremote.dtos.AuthSignInDto;
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

import java.util.*;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static fpt.fbiz.fremote.services.UserService.SERVICE_NAME;

@Service(SERVICE_NAME)
public class UserService extends BaseService<User, UserRepository> implements UserDetailsService {

    public final static String SERVICE_NAME = "USER_SERVICE";

    @Autowired
    private UserRepository userRepository;

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
    public AuthResult signIn(AuthSignInDto dto) throws Exception {
        var result = new AuthResult();

        var user = userRepository.findByEmail(dto.getEmail());

        if (user == null) {
            user = userRepository.findByUsername(dto.getUsername());
            if (user == null) {
                throw new Exception("User not found");
            }
        }

        Map<String, Object> map = user.toMap();

        String token = JWT.create()
                .withSubject(SecurityConstant.SUBJECT)
                .withClaim("claims", map)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
                .sign(HMAC512(SecurityConstant.SECRET.getBytes()));

        result.setUser(user);
        result.setToken(token);

        return result;
    }


    @Transactional
    public User signUp(
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
