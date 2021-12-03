package com.cos.security1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationYamlReadTest {
    @Autowired
    private ApplicationYamlRead applicationYamlRead;

    @Test
    void yamlFileTest() {
        String name = applicationYamlRead.getName();

        System.out.println("My name is " + name);
        assertThat(name).isEqualTo("van");
    }
}

