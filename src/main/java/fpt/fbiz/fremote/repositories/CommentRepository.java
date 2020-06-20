package fpt.fbiz.fremote.repositories;

import fpt.fbiz.fremote.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
