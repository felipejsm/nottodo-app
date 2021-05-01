package com.nssp.nottodo.dataprovider.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
public interface UserGateway {
    public UserEnt create(UserEnt type);
    public List<UserEnt> listAll();
    public Optional<UserEnt> findById(Long id);
    public Boolean update(UserEnt type);
}
