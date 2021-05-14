package com.nssp.nottodo.core.usecase.inbound;

import com.nssp.nottodo.core.usecase.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class IncludeUserInputInboundTest {
    @Autowired
    IncludeUserInputInbound include;
    @Test
    public void includeUser() {
        assertNotNull(this.include.includeUser(new UserDto()));
    }
}
