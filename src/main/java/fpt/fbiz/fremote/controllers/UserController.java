package fpt.fbiz.fremote.controllers;

import fpt.fbiz.fremote.entities.User;
import fpt.fbiz.fremote.repositories.UserRepository;
import fpt.fbiz.fremote.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public/users")
class UserController extends BaseController<User, UserRepository, UserService> {
    public UserController(UserService service) {
        super(service);
    }
}
