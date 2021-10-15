package com.cos.security1.config.oauth;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FirebaseConfig {

//  파이어베이스 init
//  초기화를 위해 파이어베이스 비공개 계정키가 필요하다. 이는 환경변수를 통해 미리 등록시켜놓는다.
    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {
        FirebaseOptions options = FirebaseOptions.builder()
                //환경변수로 부터 firebase 비공개 계정키를 불러온다. 오류가 자주 나므로 환경변수 확인해볼 것
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .build();
        FirebaseApp.initializeApp(options);
        return FirebaseAuth.getInstance(FirebaseApp.getInstance());
    }
}