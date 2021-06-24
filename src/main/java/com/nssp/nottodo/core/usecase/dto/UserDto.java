package com.nssp.nottodo.core.usecase.dto;

import java.util.List;

public class UserDto {
    public String id;
    public String name;
    public String email;
    public String nick;
    public boolean enabled;
    public List<NotToDoDto> notToDoDtoList;
}
