package com.nssp.nottodo.core.usecase.inbound;

import com.nssp.nottodo.core.usecase.dto.NotToDoDto;
import com.nssp.nottodo.core.usecase.dto.UserDto;

import java.util.List;

public interface IncludeNotToDoInputOutbound {
    NotToDoDto includeNotToDo(NotToDoDto notToDoDto);

    List<NotToDoDto> listAllByUserId(Long id);

    NotToDoDto updateUser(NotToDoDto userDto);

    NotToDoDto findUserById(Long id);

}
