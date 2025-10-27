package ua.edu.ucu.apps.lab6;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import ua.edu.ucu.apps.lab6.controller.FlowerController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FlowerController.class)
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testGetFlowersReturnsJson() throws Exception {
        mockMvc.perform(get("/flowers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
}