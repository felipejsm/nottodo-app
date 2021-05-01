package com.nssp.nottodo.core.usecase.inbound;

import com.nssp.nottodo.core.usecase.dto.UserDto;

public interface IncludeUserInputInbound {
    public UserDto includeUser(UserDto userDto);
}
