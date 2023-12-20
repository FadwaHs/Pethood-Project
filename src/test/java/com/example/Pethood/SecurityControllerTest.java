package com.example.Pethood;

import com.example.Pethood.Configurationlayer.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @ExtendWith annotation Specifies the use of the Spring extension for JUnit 5.
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {PethoodApplication.class, SecurityConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// Auto_configures the MockMvc instance to be injected.
@AutoConfigureMockMvc
public class SecurityControllerTest {

    // using MockMvc to verify the behavior of the SecurityController when processing a login request.
    @Autowired
    private MockMvc mockMvc;

    /*
     * This test is essentially checking whether a POST request to "/api/Auth/login"
     * with the specified parameters returns an HTTP status code of 200 (OK).
     */
    @Test
    public void testLogin() throws Exception {

        mockMvc.perform(post("/api/Auth/login")
                        .param("email", "Admin@gmail.com")
                        .param("password", "123456")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED) )
                .andExpect(status().isOk());
    }
}



