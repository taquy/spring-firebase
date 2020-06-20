package fpt.fbiz.fremote.controllers;

import fpt.fbiz.fremote.entities.Comment;
import fpt.fbiz.fremote.entities.User;
import fpt.fbiz.fremote.repositories.CommentRepository;
import fpt.fbiz.fremote.repositories.UserRepository;
import fpt.fbiz.fremote.services.CommentService;
import fpt.fbiz.fremote.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("private/comments")
class CommentController extends BaseController<Comment, CommentRepository, CommentService> {
    public CommentController(CommentService service) {
        super(service);
    }
}
