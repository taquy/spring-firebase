package fpt.fbiz.fremote.services;

import fpt.fbiz.fremote.entities.Comment;
import fpt.fbiz.fremote.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends BaseService<Comment, CommentRepository> {
    public CommentService(CommentRepository repository) {
        super(repository);
    }
}
