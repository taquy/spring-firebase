package fpt.fbiz.fremote.shared;

import fpt.fbiz.fremote.configs.auth.FirebaseTokenHolder;
import fpt.fbiz.fremote.exceptions.FirebaseTokenInvalidException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.util.StringUtils;

public class FirebaseParser {
    public FirebaseTokenHolder parseToken(String idToken) {
        if (StringUtils.isEmpty(idToken)) {
            throw new IllegalArgumentException("FirebaseTokenBlank");
        }
        try {
            FirebaseToken authTask = FirebaseAuth.getInstance().verifyIdToken(idToken);
            return new FirebaseTokenHolder(authTask);
        } catch (Exception e) {
            throw new FirebaseTokenInvalidException(e.getMessage());
        }
    }
}
