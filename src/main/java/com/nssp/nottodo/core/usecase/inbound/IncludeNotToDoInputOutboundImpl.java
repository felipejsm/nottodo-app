package com.nssp.nottodo.core.usecase.inbound;

import com.nssp.nottodo.core.usecase.dto.NotToDoDto;
import com.nssp.nottodo.core.usecase.dto.UserDto;
import com.nssp.nottodo.dataprovider.nottodo.NotToDoEnt;
import com.nssp.nottodo.dataprovider.nottodo.NotToDoGateway;
import com.nssp.nottodo.dataprovider.user.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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
        var ent = dtoToEnt(notToDoDto);
        var retornoEnt = this.repository.create(ent);
        notToDoDto.id = retornoEnt.getId();

        return notToDoDto;
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
    public NotToDoDto updateNotToDo(NotToDoDto notToDoDto) {
        var exist = this.repository.findById(notToDoDto.id);
        exist.ifPresent(up -> {
            var ent = dtoToEnt(notToDoDto);
            ent.setUpdateDate(LocalDateTime.now().toString());
            this.repository.update(ent);
        });
        return notToDoDto;
    }

    @Override
    public NotToDoDto findNotToDoById(Long id) {
        var exist = this.repository.findById(id);
        var dto = new NotToDoDto();
        if(exist.isPresent()) {
            return entToDto(exist.get());
        }
        return dto;
    }

    private NotToDoEnt dtoToEnt(NotToDoDto notToDoDto) {
        var ent = new NotToDoEnt();
        ent.setId(notToDoDto.id);
        ent.setDescription(notToDoDto.description);
        ent.setItemName(notToDoDto.itemName);
        ent.setEnabled(notToDoDto.enabled);
        ent.setDate(notToDoDto.date);
        ent.setUpdateDate(notToDoDto.updateDate);

        var userEnt = this.userRepository.findById(notToDoDto.user.id);
        if(userEnt.isPresent())
            ent.setUserEnt(userEnt.get());
        return  ent;
    }
    private NotToDoDto entToDto(NotToDoEnt ent) {
        var dto = new NotToDoDto();
        dto.id = ent.getId();
        var userEnt = this.userRepository.findById(ent.getUserEnt().getId());
        userEnt.ifPresent(user -> {
            UserDto userDto = new UserDto();
            userDto.id = user.getId();
            userDto.nick = user.getNick();
            userDto.name = user.getName();
            userDto.email = user.getEmail();
            userDto.enabled = user.isEnabled();
            dto.user = userDto;
        });
        dto.enabled = ent.isEnabled();
        dto.date = ent.getDate();
        dto.updateDate = ent.getUpdateDate();
        dto.description = ent.getDescription();
        dto.itemName = ent.getItemName();
        return dto;
    }
}
