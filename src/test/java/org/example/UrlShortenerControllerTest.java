package org.example;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc

public class UrlShortenerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Testet: POST /shorten erstellt eine Kurz-ID
    @Test
    void testShortenEndpoint()throws Exception{
        mockMvc.perform(post("/shorten")
                .contentType("application/json")
                .content("{\"url\":\"https://khaled.fayzi.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    // Testet: GET /{id} gibt 404, wenn ID nicht existiert
    @Test
    //Testet, ob GET /{id} bei unbekannter ID 404 zurückgibt
    void testRedirectEndpoint_NotFound()throws Exception{
        mockMvc.perform(get("/unknownId"))
                .andExpect(status().isNotFound());
    }
}
