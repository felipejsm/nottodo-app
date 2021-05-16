package com.nssp.nottodo.entrypoint.http;

import com.nssp.nottodo.core.usecase.dto.NotToDoDto;
import com.nssp.nottodo.core.usecase.dto.UserDto;
import com.nssp.nottodo.core.usecase.inbound.IncludeNotToDoInputOutbound;
import com.nssp.nottodo.dataprovider.nottodo.NotToDoEnt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.PlatformTransactionManager;

import javax.transaction.Transaction;
import javax.transaction.Transactional;
import javax.transaction.TransactionalException;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NotToDoControllerTest {
    @Autowired
    MockMvc mock;

    @Autowired
    IncludeNotToDoInputOutbound include;
/*
    @Test
    void givenNotToDo_whenFindAllNotToDo_thenStatus200() throws Exception {
        var dtoInput = new NotToDoDto();
        dtoInput.id = 1L;
        dtoInput.user = new UserDto();
        dtoInput.date = LocalDateTime.now().toString();
        dtoInput.description = "Descrição do Item";
        dtoInput.itemName = "Nome do Item";
        dtoInput.enabled = true;
        this.mock.perform(get("/v1/items/","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].enabled").value(dtoInput.enabled));
    }

*/
}
