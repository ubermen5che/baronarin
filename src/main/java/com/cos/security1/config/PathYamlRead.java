package com.cos.security1.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//path.yml에서 업로드path와 sign_path 정보를 불러올 때 mapping하는 class 정의
@Configuration
@PropertySource(value = "classpath:path.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "path")
public class PathYamlRead {
    private String sign_path;
    private String upload_path;

    public String getSign_path() {
        return sign_path;
    }

    public void setSign_path(String sign_path) {
        this.sign_path = sign_path;
    }

    public String getUpload_path() {
        return upload_path;
    }

    public void setUpload_path(String upload_path) {
        this.upload_path = upload_path;
    }
}