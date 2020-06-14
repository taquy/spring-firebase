package com.example.demo.shared;

import com.example.demo.configs.auth.FirebaseTokenHolder;
import com.example.demo.exceptions.FirebaseTokenInvalidException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.tasks.Task;
import com.google.firebase.tasks.Tasks;
import org.springframework.util.StringUtils;

public class FirebaseParser {
    public FirebaseTokenHolder parseToken(String idToken) {
        if (StringUtils.isEmpty(idToken)) {
            throw new IllegalArgumentException("FirebaseTokenBlank");
        }
        try {
            Task<FirebaseToken> authTask = FirebaseAuth.getInstance().verifyIdToken(idToken);

            Tasks.await(authTask);

            return new FirebaseTokenHolder(authTask.getResult());
        } catch (Exception e) {
            throw new FirebaseTokenInvalidException(e.getMessage());
        }
    }
}
