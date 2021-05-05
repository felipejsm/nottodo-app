package com.nssp.nottodo.dataprovider;
import com.nssp.nottodo.dataprovider.nottodo.NotToDoEnt;
import com.nssp.nottodo.dataprovider.nottodo.PersistNotToDoEnt;
import com.nssp.nottodo.dataprovider.user.PersistUserEnt;
import com.nssp.nottodo.dataprovider.user.UserEnt;
import org.junit.jupiter.api.DisplayName;
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

    @Autowired
    PersistUserEnt user;
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
        var retorno = persistNotToDoEnt.listAllByUserId(1L);
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
        notToDoEnt.setUpdateDate(LocalDateTime.now().toString());

        var retorno = this.persistNotToDoEnt.update(notToDoEnt);
        assertTrue(retorno);
    }

    @Test
    @DisplayName("Criação de item com um usuario")
    void createWithUser() {
        var userEnt = new UserEnt();
        userEnt.setNick("MrTomato");
        userEnt.setName("Stephen");
        userEnt.setEmail("s.nephent@gmail.com");

        var retornoUser = this.user.create(userEnt);
        var notToDo = new NotToDoEnt();
        notToDo.setDate(LocalDateTime.now().toString());
        notToDo.setDescription("Não comprar pão na padaria Flor de Coimbra porque é muito ruim o atendimento e não vale a pena");
        notToDo.setItemName("Pão");
        notToDo.setEnabled(true);
        notToDo.setUserEnt(retornoUser);
        var retornoNotToDo = this.persistNotToDoEnt.create(notToDo);
        retornoUser.setNotToDoEntList(retornoNotToDo);
        var retornoUserComNotToDo = this.user.update(retornoUser);
        assertTrue(retornoUserComNotToDo);
        assertNotNull(retornoNotToDo);
    }

}
