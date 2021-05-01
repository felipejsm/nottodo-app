package com.nssp.nottodo.core.entities;

import org.junit.jupiter.api.Test;

public class NotToDoTest {
    @Test
    void CreateNotToDoTest() {
        NotToDo notToDo = new NotToDo();
        notToDo.setDate("21/04/2021");
        notToDo.setDescription("Worst pepperoni pizza ever");
        notToDo.setEnabled(true);
        notToDo.setItemName("Paolo's Pizza");
        notToDo.setUser(new User());
    }
}
