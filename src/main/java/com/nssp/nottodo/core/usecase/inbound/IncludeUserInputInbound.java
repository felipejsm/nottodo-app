package com.nssp.nottodo.core.usecase.inbound;

import com.nssp.nottodo.core.usecase.dto.UserDto;

public interface IncludeUserInputInbound {
    UserDto includeUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);
}
