package fpt.fbiz.fremote.controllers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;
import fpt.fbiz.fremote.dtos.AuthSignInDto;
import fpt.fbiz.fremote.dtos.AuthSignUpDto;
import fpt.fbiz.fremote.entities.User;
import fpt.fbiz.fremote.services.UserService;
import fpt.fbiz.fremote.utils.MiscUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("")
public class AuthController {

    @Autowired
    UserService userService;

    @GetMapping("private")
    public Object test() {
        return "hello private";
    }

    @PostMapping("public/register")
    public Object register(@RequestBody AuthSignUpDto dto) throws ExecutionException, InterruptedException {
        var user = userService.register(dto);

        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef = db.collection("users").document(user.getEmail());

        System.out.println(user.toMap());

        ApiFuture<WriteResult> result = docRef.set(user.toMap());
        return result.get().getUpdateTime().toString();
    }

    @PostMapping("public/sign-in")
    public Object signIn(@RequestBody AuthSignInDto dto) throws ExecutionException, InterruptedException {
        var instance = FirebaseAuth.getInstance();

        if (StringUtils.isEmpty(dto.getEmail())) {
            return null;
        }

        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef = db.collection("users").document(dto.getEmail());
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            System.out.println("Document data: " + document.getData());
            var data = document.getData();
            var user = MiscUtil.convertMapToObject(data, User.class);
            return user;
        }

        System.out.println("No such document!");

        return null;
    }

    @GetMapping("public/users")
    public Object getUsers() {
        return null;
    }
}
