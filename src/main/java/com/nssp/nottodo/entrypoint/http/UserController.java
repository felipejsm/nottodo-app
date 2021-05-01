package com.nssp.nottodo.entrypoint.http;

import com.nssp.nottodo.core.usecase.dto.UserDto;
import com.nssp.nottodo.core.usecase.inbound.IncludeUserInputInbound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class UserController {
    private IncludeUserInputInbound inputInbound;
    @Autowired
    void setInputInbound(IncludeUserInputInbound inputInbound){
        this.inputInbound = inputInbound;
    }
    @PostMapping("/users")
    public UserDto create(@RequestBody UserDto userDto){
        var retorno = this.inputInbound.includeUser(userDto);
        System.out.println(retorno);
        return retorno;
    }
}
