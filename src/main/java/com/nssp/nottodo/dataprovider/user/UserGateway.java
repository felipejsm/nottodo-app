package com.nssp.nottodo.dataprovider.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
public interface UserGateway {
    UserEnt create(UserEnt type);
    List<UserEnt> listAll();
    Optional<UserEnt> findById(Long id);
    Boolean update(UserEnt type);
}
