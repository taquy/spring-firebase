package com.example.demo.controllers;

import com.example.demo.dtos.AuthRegisterDto;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    @GetMapping("private")
    public Object test() {
        return "hello private";
    }

    @PostMapping("public/register")
    public Object register(@RequestBody AuthRegisterDto dto) throws ExecutionException, InterruptedException {
        var user = userService.register(dto);

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(user.getEmail()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    @GetMapping("public/users")
    public Object getUsers() {
        return userRepository.findAll();
    }
}
