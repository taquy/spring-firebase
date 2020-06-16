package fpt.fbiz.fremote.configs;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@Configuration
public class FirebaseConfig {

    @Bean
    public DatabaseReference firebaseDatabase() {
        DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();
        return firebase;
    }

    @Value("${firebase.database.url}")
    private String databaseUrl;

    @Value("${firebase.config.path}")
    private String configPath;

    @PostConstruct
    public void init() throws IOException {
        /**
         * https://firebase.google.com/docs/server/setup
         *
         * Create service account , download json
         */
        InputStream serviceAccount = FirebaseConfig.class.getClassLoader().getResourceAsStream(configPath);

        assert serviceAccount != null;
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(databaseUrl)
                .build();

        FirebaseApp.initializeApp(options);
    }
}
