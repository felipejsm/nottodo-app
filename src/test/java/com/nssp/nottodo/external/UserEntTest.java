package com.nssp.nottodo.external;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@ComponentScan
class UserEntTest {
    @Autowired
    PersistUserEnt user;
    @Autowired
    PersistNotToDoEnt notToDo;
    private NotToDoEnt notToDoEnt;
    @BeforeAll
    void loadNotToDoEnt() {
        var notToDoType = new NotToDoEnt();
        notToDoType.setDate(LocalDateTime.now().toString());
        notToDoType.setDescription("Não comprar pão na padaria Flor de Coimbra porque é muito ruim o atendimento e não vale a pena");
        notToDoType.setItemName("Pão");
        notToDoType.setEnabled(true);
        this.notToDoEnt = this.notToDo.create(notToDoType);
    }
    @Test
    @DisplayName("Instantiate a User")
    void CreateUserEnt() {
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
        PersistUserEntTest();
        var users = user.listAll();
        assertNotNull(users, "not null");
    }
    @Disabled
    @Test
    @DisplayName("Find User By Id")
    void FindByIdTest() throws InterruptedException {
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