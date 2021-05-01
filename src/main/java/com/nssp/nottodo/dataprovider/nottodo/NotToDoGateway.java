package com.nssp.nottodo.dataprovider.nottodo;

import java.util.List;
import java.util.Optional;

public interface NotToDoGateway {
    public NotToDoEnt create(NotToDoEnt type);
    public List<NotToDoEnt> listAll();
    public Optional<NotToDoEnt> findById(Long id);
    public Boolean update(NotToDoEnt type);
}
