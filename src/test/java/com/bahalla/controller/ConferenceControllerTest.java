package com.bahalla.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConferenceControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void aboutReturnsConferenceInfo() throws Exception {
        this.mockMvc.perform(get("/about"))
                .andExpect(status().isOk())
                .andExpect(content().string("This conference is about security and tests"));
    }

    @Test
    public void greetingsReturnsHelloAndUsername() throws Exception {
        this.mockMvc.perform(get("/greeting")
                        .with(user("Taoufiq"))
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Taoufiq!"));
    }

    @Test
    public void greetingsWithUnauthenticatedUserReturn401() throws Exception {
        this.mockMvc.perform(get("/greeting"))
                .andExpect(status().isUnauthorized());
    }
}