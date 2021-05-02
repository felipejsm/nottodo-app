package com.nssp.nottodo.core.usecase.inbound;
import com.nssp.nottodo.dataprovider.user.UserEnt;
import com.nssp.nottodo.dataprovider.user.UserGateway;
import com.nssp.nottodo.core.usecase.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IncludeUserInputInboundImpl implements IncludeUserInputInbound {
    private UserGateway repository;
    @Autowired
    public void setUserRepository(UserGateway repository) {
        this.repository = repository;
    }

    @Override
    public UserDto includeUser(UserDto userDto) {
        UserEnt userEnt = new UserEnt();
        userEnt.setEmail(userDto.email);
        userEnt.setName(userDto.name);
        userEnt.setNick(userDto.nick);
        userEnt.setEnabled(true);
        var newUserEnt = this.repository.create(userEnt);
        userDto.id = newUserEnt.getId();
        userDto.enabled = newUserEnt.isEnabled();
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        var exist = this.repository.findById(userDto.id);
        if(exist.isPresent()) {
            var userEnt = new UserEnt();
            userEnt.setId(userDto.id);
            userEnt.setEmail(userDto.email);
            userEnt.setEnabled(userDto.enabled);
            userEnt.setNick(userDto.nick);
            userEnt.setName(userDto.name);
            this.repository.update(userEnt);
        }
        return userDto;
    }

    @Override
    public UserDto findUserById(Long id) {
        var retorno = this.repository.findById(id);
        var userDto = new UserDto();
        retorno.ifPresent( ent -> {
            userDto.enabled = ent.isEnabled();
            userDto.nick = ent.getNick();
            userDto.id = ent.getId();
            userDto.email = ent.getEmail();
            userDto.name = ent.getName();
        });
        return userDto;
    }

    @Override
    public List<UserDto> listAll() {
        var users = this.repository.listAll();
        return users.stream().map(ent -> {
            var userDto = new UserDto();
            userDto.enabled = ent.isEnabled();
            userDto.nick = ent.getNick();
            userDto.id = ent.getId();
            userDto.email = ent.getEmail();
            userDto.name = ent.getName();
            return userDto;
        }).collect(Collectors.toList());
    }
}
