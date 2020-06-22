package fpt.fbiz.fremote.repositories;

import fpt.fbiz.fremote.entities.TaskUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TaskUserRepository extends JpaRepository<TaskUser, Long> {
    Page<TaskUser> findAllByEmployeeId(Long employeeId, Pageable page);
}
