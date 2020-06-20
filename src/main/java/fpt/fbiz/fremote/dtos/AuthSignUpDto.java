package fpt.fbiz.fremote.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class AuthSignUpDto {
    private String username;
    private String email;
    private String password;
}
