package fpt.fbiz.fremote.controllers;

import fpt.fbiz.fremote.dtos.AuthSignInDto;
import fpt.fbiz.fremote.dtos.AuthSignUpDto;
import fpt.fbiz.fremote.services.UserService;
import fpt.fbiz.fremote.shared.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("sign-up")
    public Object register(
            @RequestBody AuthSignUpDto dto
    ) {
        var response = userService.signUp(dto);
        return ApiResponse.success(response);
    }

    @PostMapping("sign-in")
    public Object signIn(
            @RequestBody AuthSignInDto dto
    ) {
        try {
            var response = userService.signIn(dto);
            return ApiResponse.success(response);
        } catch (Exception ex) {
            return ApiResponse.error(ex.getMessage());
        }
    }
}
