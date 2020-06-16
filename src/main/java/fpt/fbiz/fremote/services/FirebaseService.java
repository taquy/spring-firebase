package fpt.fbiz.fremote.services;

import fpt.fbiz.fremote.conditionals.FirebaseCondition;
import fpt.fbiz.fremote.configs.auth.FirebaseTokenHolder;
import fpt.fbiz.fremote.shared.FirebaseParser;
import org.springframework.stereotype.Service;

@Service
@FirebaseCondition
public class FirebaseService {
    public FirebaseTokenHolder parseToken(String firebaseToken) {
        return new FirebaseParser().parseToken(firebaseToken);
    }
}
