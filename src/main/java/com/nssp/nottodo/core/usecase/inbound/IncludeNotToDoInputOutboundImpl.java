package com.nssp.nottodo.core.usecase.inbound;

import com.nssp.nottodo.core.usecase.dto.NotToDoDto;
import com.nssp.nottodo.core.usecase.dto.UserDto;
import com.nssp.nottodo.dataprovider.nottodo.NotToDoGateway;
import com.nssp.nottodo.dataprovider.user.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IncludeNotToDoInputOutboundImpl implements IncludeNotToDoInputOutbound {
    private NotToDoGateway repository;
    private UserGateway userRepository;
    @Autowired
    void setNotToDoGateway(NotToDoGateway repository) {
        this.repository = repository;
    }
    @Autowired
    void setUserRepository(UserGateway userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public NotToDoDto includeNotToDo(NotToDoDto notToDoDto) {

        return null;
    }

    @Override
    public List<NotToDoDto> listAllByUserId(Long id) {
        var notToDos = this.repository.listAllByUserId(id);
        List<NotToDoDto> not = new ArrayList<>();
        notToDos.forEach(n -> {
            var notToDoDto = new NotToDoDto();
            notToDoDto.itemName = n.getItemName();
            notToDoDto.description = n.getDescription();
            notToDoDto.date = n.getDate();
            notToDoDto.id = n.getId();
            notToDoDto.enabled = n.isEnabled();
            UserDto userDto = new UserDto();
            userDto.name = n.getUserEnt().getName();
            userDto.email = n.getUserEnt().getEmail();
            userDto.nick = n.getUserEnt().getNick();
            userDto.id = n.getUserEnt().getId();
            userDto.enabled = n.getUserEnt().isEnabled();
            notToDoDto.user = userDto;
            not.add(notToDoDto);
        });
        return not;
    }

    @Override
    public NotToDoDto updateNotToDo(NotToDoDto userDto) {
        return null;
    }

    @Override
    public NotToDoDto findNotToDoById(Long id) {
        return null;
    }
}
