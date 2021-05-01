package com.nssp.nottodo.entrypoint.http;
import com.nssp.nottodo.core.usecase.dto.UserDto;
import com.nssp.nottodo.core.usecase.inbound.IncludeUserInputInbound;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    MockMvc mock;

    @Autowired
    IncludeUserInputInbound include;

    @Test
    void givenUsers_whenCreateUsers_thenStatus200() throws Exception {
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
        var payload = String.format("{\"name\":\"%s\",\"nick\":\"%s\",\"email\":\"%s\"}", dtoInput.name, dtoInput.nick, dtoInput.email);
         mock.perform(post("/v1/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content(payload))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(dtoOutput.name))
        .andExpect(jsonPath("$.nick").value(dtoOutput.nick))
        .andExpect(jsonPath("$.email").value(dtoOutput.email))
        .andExpect(jsonPath("$.enabled").value(dtoOutput.enabled))
        .andExpect(jsonPath("$.id").value(dtoOutput.id));

    }
}
