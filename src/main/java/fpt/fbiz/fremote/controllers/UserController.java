package fpt.fbiz.fremote.controllers;

import fpt.fbiz.fremote.entities.User;
import fpt.fbiz.fremote.repositories.UserRepository;
import fpt.fbiz.fremote.services.UserService;
import fpt.fbiz.fremote.shared.ApiResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("private/users")
class UserController extends BaseController<User, UserRepository, UserService> {
    public UserController(UserService service) {
        super(service);
    }

    @GetMapping("auth")
    public ApiResponse getAuthUser(
            Authentication auth
    ) {
        return ApiResponse.success(auth);
    }
}
