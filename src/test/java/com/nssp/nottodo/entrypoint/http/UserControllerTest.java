package com.nssp.nottodo.entrypoint.http;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nssp.nottodo.core.usecase.dto.UserDto;
import com.nssp.nottodo.core.usecase.inbound.IncludeUserInputInbound;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    MockMvc mock;
    @MockBean
    IncludeUserInputInbound include;

    @Test
    void givenUsers_whenCreateUsers_thenStatus201() throws Exception {
        var dtoInput = new UserDto();
        dtoInput.email = "myemail@email.com";
        dtoInput.name = "João da Silva";
        dtoInput.nick = "js";

        var dtoOutput = new UserDto();
        dtoOutput.email = "myemail@email.com";
        dtoOutput.name = "João da Silva";
        dtoOutput.nick = "js";
        dtoOutput.id = 1L;
        dtoOutput.enabled = true;
        when(include.includeUser(dtoInput)).thenReturn(dtoOutput);

        var payload = String.format("{\"name\":\"%s\",\"nick\":\"%s\",\"email\":\"%s\"}", dtoInput.name, dtoInput.nick, dtoInput.email);
         mock.perform(post("/v1/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content(payload))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value(dtoOutput.name))
        .andExpect(jsonPath("$[0].nick").value(dtoOutput.nick))
        .andExpect(jsonPath("$[0].email").value(dtoOutput.email))
        .andExpect(jsonPath("$[0].enabled").value(dtoOutput.enabled))
        .andExpect(jsonPath("$[0].id").value(dtoOutput.id));

    }
}
