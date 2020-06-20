package fpt.fbiz.fremote.repositories;

import fpt.fbiz.fremote.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);

    List<User> findByUsername(String username);
}
