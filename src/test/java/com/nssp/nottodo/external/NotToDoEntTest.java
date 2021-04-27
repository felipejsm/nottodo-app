package com.nssp.nottodo.external;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class NotToDoEntTest {
    @Autowired
    PersistNotToDoEnt persistNotToDoEnt;

    @Test
    void CreateNotToDoEnt() {
        var notToDoEnt = new NotToDoEnt();
        notToDoEnt.setItemName("");
        notToDoEnt.setDescription("");
        notToDoEnt.setDate("");
        notToDoEnt.setEnabled(true);

    }

    @Test
    NotToDoEnt PersistNotToDoEntTest() {
        var notToDo = new NotToDoEnt();
        notToDo.setDate(LocalDateTime.now().toString());
        notToDo.setDescription("Não comprar pão na padaria Flor de Coimbra porque é muito ruim o atendimento e não vale a pena");
        notToDo.setItemName("Pão");
        notToDo.setEnabled(true);
        var retorno = persistNotToDoEnt.create(notToDo);

        assertNotNull(retorno, "Not null");
        return retorno;
    }

    @Test
    void RetrieveNotToDoEntTest() {
        PersistNotToDoEntTest();
        var retorno = persistNotToDoEnt.listAll();
        assertNotNull(retorno);
    }

    @Test
    void FindByIdTest() {
        var notToDoEnt = PersistNotToDoEntTest();
        var retorno = this.persistNotToDoEnt.findById(notToDoEnt.getId());
        assertTrue(retorno.isPresent());
    }

    @Test
    void UpdateTest() {
        var notToDoEnt = PersistNotToDoEntTest();
        notToDoEnt.setItemName("Pão da Padaria");
        notToDoEnt.setDate(LocalDateTime.now().toString());
        var retorno = this.persistNotToDoEnt.update(notToDoEnt);
        assertTrue(retorno);
    }


}
