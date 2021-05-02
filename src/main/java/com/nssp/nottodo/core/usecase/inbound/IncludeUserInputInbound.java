package com.nssp.nottodo.core.usecase.inbound;

import com.nssp.nottodo.core.usecase.dto.UserDto;

import java.util.List;

public interface IncludeUserInputInbound {
    UserDto includeUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    UserDto findUserById(Long id);

    List<UserDto> listAll();
}
