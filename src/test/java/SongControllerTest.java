package com.mytask.songselector;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

//Configures spring boot to test SongController class
@WebMvcTest(SongController.class)
public class SongControllerTest {
    /*Enables automatic dependency injection
    mockMvc is a spring testing tool simulating HTTP requests and responses
    GET is tested here*/
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void home_shouldReturnIndexView() throws Exception {
        //Checks if response status is 200 and if returned view name is index
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}