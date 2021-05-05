package com.nssp.nottodo.dataprovider.nottodo;

import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NotToDoGateway {
    NotToDoEnt create(NotToDoEnt type);
    List<NotToDoEnt> listAllByUserId(Long id);
    Optional<NotToDoEnt> findById(Long id);
    Boolean update(NotToDoEnt type);
}
