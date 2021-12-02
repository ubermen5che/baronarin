package com.cos.security1.applicationyml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/* application.yml에 설정한 파일 업로드 경로를 가져옴 */

@Component
@ConfigurationProperties(prefix = "file")
public class FileUploadProperties {

    private String location;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
