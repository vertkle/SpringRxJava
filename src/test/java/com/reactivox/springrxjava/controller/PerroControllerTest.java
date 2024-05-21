package com.reactivox.springrxjava.controller;

import com.reactivox.springrxjava.service.PerroServiceImpl;
import io.reactivex.Flowable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PerroController.class)
public class PerroControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PerroServiceImpl perroService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllTest() throws Exception{
        when(perroService.findAllService()).thenReturn(Flowable.empty());

        mockMvc.perform(get("/api/perro"))
                .andExpect(status().isOk());
    }
}
