package fpt.fbiz.fremote.repositories;

import fpt.fbiz.fremote.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
