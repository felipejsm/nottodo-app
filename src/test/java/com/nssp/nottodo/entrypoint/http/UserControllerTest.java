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
    void givenUser_whenFindAllUsers_thenStatus200() throws Exception {
        givenUsers_whenUpdateUsers_thenStatus200();
        var dtoInput = new UserDto();
        dtoInput.id = 1L;
        dtoInput.email = "terminator.arnold@email.com";
        dtoInput.name = "Arnold";
        dtoInput.nick = "hasta_la_vista";
        dtoInput.enabled = true;
        this.mock.perform(get("/v1/users/","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(dtoInput.name))
                .andExpect(jsonPath("$[0].nick").value(dtoInput.nick))
                .andExpect(jsonPath("$[0].email").value(dtoInput.email))
                .andExpect(jsonPath("$[0].enabled").value(dtoInput.enabled))
                .andExpect(jsonPath("$[0].id").value(dtoInput.id));
    }
    @Test
    void givenUser_whenFindUserById_thenStatus302() throws Exception {
        givenUsers_whenUpdateUsers_thenStatus200();
        var dtoInput = new UserDto();
        dtoInput.id = 1L;
        dtoInput.email = "terminator.arnold@email.com";
        dtoInput.name = "Arnold";
        dtoInput.nick = "hasta_la_vista";
        dtoInput.enabled = true;
        this.mock.perform(get("/v1/users/{id}","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.name").value(dtoInput.name))
                .andExpect(jsonPath("$.nick").value(dtoInput.nick))
                .andExpect(jsonPath("$.email").value(dtoInput.email))
                .andExpect(jsonPath("$.enabled").value(dtoInput.enabled))
                .andExpect(jsonPath("$.id").value(dtoInput.id));

    }
    @Test
    void givenUsers_whenUpdateUsers_thenStatus200() throws Exception {
        givenUsers_whenCreateUsers_thenStatus200();
        // objeto de entrada para ser atualizado o nome
        var dtoInput = new UserDto();
        dtoInput.id = 1L;
        dtoInput.email = "terminator.arnold@email.com";
        dtoInput.name = "Arnold";
        dtoInput.nick = "hasta_la_vista";
        dtoInput.enabled = true;

        var payload = String.format("{\"id\":\"%s\",\"email\":\"%s\",\"name\":\"%s\",\"nick\":\"%s\", \"enabled\":\"%s\"}",
                dtoInput.id,
                dtoInput.email,
                dtoInput.name,
                dtoInput.nick,
                dtoInput.enabled);
        // MockMVC <3
        mock.perform(put("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(dtoInput.name))
                .andExpect(jsonPath("$.nick").value(dtoInput.nick))
                .andExpect(jsonPath("$.email").value(dtoInput.email))
                .andExpect(jsonPath("$.enabled").value(dtoInput.enabled))
                .andExpect(jsonPath("$.id").value(dtoInput.id));
    }
    @Test
    void givenUsers_whenCreateUsers_thenStatus200() throws Exception {

        // Objeto de entrada
        var dtoInput = new UserDto();
        dtoInput.email = "myemail@email.com";
        dtoInput.name = "João da Silva";
        dtoInput.nick = "js";
        var payload = String.format("{\"name\":\"%s\",\"nick\":\"%s\",\"email\":\"%s\"}", dtoInput.name, dtoInput.nick, dtoInput.email);

        // Objeto de saída esperada
        var dtoOutput = new UserDto();
        dtoOutput.email = "myemail@email.com";
        dtoOutput.name = "João da Silva";
        dtoOutput.nick = "js";
        dtoOutput.id = 1L;
        dtoOutput.enabled = true;

        // MockMVC <3
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
