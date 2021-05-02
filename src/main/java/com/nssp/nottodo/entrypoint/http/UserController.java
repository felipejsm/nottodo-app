package com.nssp.nottodo.entrypoint.http;

import com.nssp.nottodo.core.usecase.dto.UserDto;
import com.nssp.nottodo.core.usecase.inbound.IncludeUserInputInbound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PreUpdate;

@RestController
@RequestMapping("/v1")
public class UserController {
    private IncludeUserInputInbound inputInbound;
    @Autowired
    void setInputInbound(IncludeUserInputInbound inputInbound){
        this.inputInbound = inputInbound;
    }
    @PostMapping("/users")
    public UserDto create(@RequestBody UserDto userDto) {
       return this.inputInbound.includeUser(userDto);
    }

    @PutMapping("/users")
    public UserDto update(@RequestBody UserDto userDto) {
        return this.inputInbound.updateUser(userDto);
    }
}
