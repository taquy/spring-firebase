package fpt.fbiz.fremote.facades;

import fpt.fbiz.fremote.entities.User;
import fpt.fbiz.fremote.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

public interface AuthFacade {
    Authentication getAuthentication();

    User getAuthUser();
}

@Component
class AuthFacadeImpl implements AuthFacade {

    @Autowired
    UserRepository userRepository;

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public User getAuthUser() {
        var auth = (User) getAuthentication().getPrincipal();
        return userRepository.findByUsername(auth.getUsername()).get(0);
    }
}