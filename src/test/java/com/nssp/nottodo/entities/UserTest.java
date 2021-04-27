package com.nssp.nottodo.entities;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class UserTest {
    @Test
    void createUserTest() {
        var user = new User();
        user.setEnabled(true);
        user.setEmail("john.doe@email.com");
        user.setName("João Ninguém");
        user.setNick("jn1999");
        Assert.notNull(user, "Object user successfully created");
    }
}
