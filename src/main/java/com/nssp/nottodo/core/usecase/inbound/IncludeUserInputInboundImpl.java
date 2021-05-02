package com.nssp.nottodo.core.usecase.inbound;
import com.nssp.nottodo.dataprovider.user.UserEnt;
import com.nssp.nottodo.dataprovider.user.UserGateway;
import com.nssp.nottodo.core.usecase.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
}
