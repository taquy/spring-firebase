package fpt.fbiz.fremote.repositories;

import fpt.fbiz.fremote.entities.Request;
import fpt.fbiz.fremote.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
