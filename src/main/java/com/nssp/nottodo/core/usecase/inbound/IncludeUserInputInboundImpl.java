package com.nssp.nottodo.core.usecase.inbound;
import com.nssp.nottodo.core.usecase.dto.NotToDoDto;
import com.nssp.nottodo.dataprovider.nottodo.NotToDoEnt;
import com.nssp.nottodo.dataprovider.user.UserEnt;
import com.nssp.nottodo.dataprovider.user.UserGateway;
import com.nssp.nottodo.core.usecase.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        if(userDto.notToDoDtoList != null) {
            userDto.notToDoDtoList.forEach(notToDoDto -> {
                var notToDoEnt = new NotToDoEnt();
                notToDoEnt.setEnabled(notToDoDto.enabled);
                notToDoEnt.setId(notToDoDto.id);
                notToDoEnt.setDate(notToDoDto.date);
                notToDoEnt.setDescription(notToDoDto.description);
                notToDoEnt.setItemName(notToDoDto.itemName);
                userEnt.setNotToDoEntList(notToDoEnt);
            });
        }
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
            if(userDto.notToDoDtoList != null) {
                userDto.notToDoDtoList.forEach(notToDoDto -> {
                    var notToDoEnt = new NotToDoEnt();
                    notToDoEnt.setEnabled(notToDoDto.enabled);
                    notToDoEnt.setId(notToDoDto.id);
                    notToDoEnt.setDate(notToDoDto.date);
                    notToDoEnt.setDescription(notToDoDto.description);
                    notToDoEnt.setItemName(notToDoDto.itemName);
                    userEnt.setNotToDoEntList(notToDoEnt);
                });
            }

            this.repository.update(userEnt);
        }
        return userDto;
    }

    @Override
    public UserDto findUserById(Long id) {
        var retorno = this.repository.findById(String.valueOf(id));
        var userDto = new UserDto();
        retorno.ifPresent( ent -> {
            userDto.enabled = ent.isEnabled();
            userDto.nick = ent.getNick();
            userDto.id = ent.getId();
            userDto.email = ent.getEmail();
            userDto.name = ent.getName();
            if(ent.getNotToDoEntList() != null) {
                ent.getNotToDoEntList().forEach(e -> {
                    var notToDoDto = new NotToDoDto();
                    notToDoDto.enabled = e.isEnabled();
                    notToDoDto.id = e.getId();
                    notToDoDto.date = e.getDate();
                    notToDoDto.description = e.getDescription();
                    notToDoDto.itemName = e.getItemName();
                    notToDoDto.user = userDto;
                    userDto.notToDoDtoList.add(notToDoDto);
                });
            }
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
            if(ent.getNotToDoEntList() != null) {
                ent.getNotToDoEntList().forEach(e -> {
                    var notToDoDto = new NotToDoDto();
                    notToDoDto.enabled = e.isEnabled();
                    notToDoDto.id = e.getId();
                    notToDoDto.date = e.getDate();
                    notToDoDto.description = e.getDescription();
                    notToDoDto.itemName = e.getItemName();
                    notToDoDto.user = userDto;
                    userDto.notToDoDtoList.add(notToDoDto);
                });
            }

            return userDto;
        }).collect(Collectors.toList());
    }
}
