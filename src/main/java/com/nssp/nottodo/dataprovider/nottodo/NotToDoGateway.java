package com.nssp.nottodo.dataprovider.nottodo;

import java.util.List;
import java.util.Optional;

public interface NotToDoGateway {
    NotToDoEnt create(NotToDoEnt type);
    List<NotToDoEnt> listAll();
    Optional<NotToDoEnt> findById(Long id);
    Boolean update(NotToDoEnt type);
}
