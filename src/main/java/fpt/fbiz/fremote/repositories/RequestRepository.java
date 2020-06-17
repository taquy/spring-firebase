package fpt.fbiz.fremote.repositories;

import fpt.fbiz.fremote.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {

}
