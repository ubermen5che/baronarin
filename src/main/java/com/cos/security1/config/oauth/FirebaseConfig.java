package com.cos.security1.config.oauth;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

//  파이어베이스 init
//  초기화를 위해 파이어베이스 비공개 계정키가 필요하다. 이는 환경변수를 통해 미리 등록시켜놓는다.
@Bean
public FirebaseAuth firebaseAuth() throws IOException{
        try{
            FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/baronarintest-firebase-adminsdk-mpzdf-3505b97c28.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(options);
        }catch (Exception e){
            e.printStackTrace();
        }
        return FirebaseAuth.getInstance(FirebaseApp.getInstance());
    }
}