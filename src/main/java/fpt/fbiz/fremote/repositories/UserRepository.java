package fpt.fbiz.fremote.repositories;

import fpt.fbiz.fremote.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);

    List<User> findByUsername(String username);

    @Query(nativeQuery = true, value = "SELECT * FROM users as e WHERE e.id IN (:ids)")
    List<User> findByIds(@Param("ids") List<Long> ids);
}
