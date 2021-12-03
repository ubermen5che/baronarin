package com.cos.security1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "van")
public class ApplicationYamlRead {

    private String name;

    public String getName() {
        return name;
    }

    //application.yml의 van.name을 set해준다.
    public void setName(String name) {
        this.name = name;
    }
}