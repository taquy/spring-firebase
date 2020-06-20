package fpt.fbiz.fremote.controllers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.FirestoreClient;
import fpt.fbiz.fremote.dtos.AuthSignInDto;
import fpt.fbiz.fremote.dtos.AuthSignUpDto;
import fpt.fbiz.fremote.entities.User;
import fpt.fbiz.fremote.services.UserService;
import fpt.fbiz.fremote.shared.ApiResponse;
import fpt.fbiz.fremote.utils.MiscUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("public/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("sign-up")
    public Object register(
            @RequestBody AuthSignUpDto dto
    ) {
        var response = userService.signUp(dto);
        return ApiResponse()

    }

    @PostMapping("sign-in")
    public Object signIn(
            @RequestBody AuthSignInDto dto
    ) throws ExecutionException, InterruptedException, FirebaseAuthException {
        var instance = FirebaseAuth.getInstance();

        if (StringUtils.isEmpty(dto.getEmail())) {
            return null;
        }

        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef = db.collection("users").document(dto.getEmail());
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            var data = document.getData();
            var user = MiscUtil.convertMapToObject(data, User.class);
            assert data != null;
            data.remove("password");

            var authUser = instance.getUserByEmail(user.getEmail());
            return instance.createCustomToken(authUser.getUid(), data);
        }
        System.out.println("No such document!");
        return null;
    }

    @GetMapping("public/users")
    public Object getUsers() {
        return null;
    }
}
