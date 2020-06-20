package fpt.fbiz.fremote.dtos;

import fpt.fbiz.fremote.entities.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class AuthResult {
    private String token;
    private User user;
}
