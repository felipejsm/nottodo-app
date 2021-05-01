package com.nssp.nottodo.dataprovider;

import com.nssp.nottodo.dataprovider.nottodo.NotToDoEnt;
import com.nssp.nottodo.dataprovider.nottodo.PersistNotToDoEnt;
import com.nssp.nottodo.dataprovider.user.PersistUserEnt;
import com.nssp.nottodo.dataprovider.user.UserEnt;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserEntTest {
    @Autowired
    PersistUserEnt user;
    @Autowired
    PersistNotToDoEnt notToDo;
    private static NotToDoEnt notToDoEnt;

    public void loadNotToDoEnt() {
        var notToDoType = new NotToDoEnt();
        notToDoType.setDate(LocalDateTime.now().toString());
        notToDoType.setDescription("Não comprar pão na padaria Flor de Coimbra porque é muito ruim o atendimento e não vale a pena");
        notToDoType.setItemName("Pão");
        notToDoType.setEnabled(true);
        notToDoEnt = notToDo.create(notToDoType);
    }
    @Test
    @DisplayName("Instantiate a User")
    void CreateUserEnt() {
        loadNotToDoEnt();
        var userEnt = new UserEnt();
        userEnt.setNotToDoEntList(notToDoEnt);
        userEnt.setEmail("john.doe@email.com");
        userEnt.setName("João Ninguém");
        userEnt.setNick("jn1999");
        assertNotNull(userEnt, "UserEnt is not null");
    }

    @Test
    @DisplayName("Instantiate an User and persist it")
    void PersistUserEntTest() {
        loadNotToDoEnt();
        var userEnt = new UserEnt();
        userEnt.setNotToDoEntList(notToDoEnt);
        userEnt.setEmail("john.doe@email.com");
        userEnt.setName("João Ninguém");
        userEnt.setNick("jn1999");
        var retorno = user.create(userEnt);
        assertNotNull(retorno, "Not null");
    }

    @Test
    @DisplayName("Retrieve all users")
    void RetrieveUserEntTest() {
        loadNotToDoEnt();
        PersistUserEntTest();
        var users = user.listAll();
        assertNotNull(users, "not null");
    }
    @Test
    @DisplayName("Find User By Id")
    void FindByIdTest() throws InterruptedException {
        loadNotToDoEnt();
        var userEnt = new UserEnt();
        userEnt.setNotToDoEntList(notToDoEnt);
        userEnt.setEmail("john.doe@email.com");
        userEnt.setName("João Ninguém");
        userEnt.setNick("jn1999");
        var retorno = user.create(userEnt);
        userEnt.setNick("jooooe");
        var retorno2 = user.create(userEnt);
        Optional<UserEnt> userEntOptional = user.findById(retorno.getId());
        assertTrue(userEntOptional.isPresent());
    }

    @Test
    @DisplayName("Update User")
    void UpdateUserTest() {
        loadNotToDoEnt();
        var userEnt = new UserEnt();
        userEnt.setNotToDoEntList(notToDoEnt);
        userEnt.setEmail("john.doe@email.com");
        userEnt.setName("João Ninguém");
        userEnt.setNick("jn1999");
        var retorno = user.create(userEnt);
        retorno.setEnabled(true);
        assertTrue(user.update(retorno));
    }


}