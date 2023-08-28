package com.tc.training.pizzaDelivery.config;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp firebaseApp() {
        FirebaseApp firebaseApp = null;

        try {
            InputStream serviceAccount = FirebaseConfig.class.getResourceAsStream("/serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            firebaseApp = FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            // Handle the exception appropriately, such as logging an error or throwing a runtime exception
            e.printStackTrace();
        }

        return firebaseApp;
    }
}
