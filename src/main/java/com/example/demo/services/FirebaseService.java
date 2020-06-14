package com.example.demo.services;

import com.example.demo.conditionals.FirebaseCondition;
import com.example.demo.configs.auth.FirebaseTokenHolder;
import com.example.demo.shared.FirebaseParser;
import org.springframework.stereotype.Service;

@Service
@FirebaseCondition
public class FirebaseService {
    public FirebaseTokenHolder parseToken(String firebaseToken) {
        return new FirebaseParser().parseToken(firebaseToken);
    }
}
