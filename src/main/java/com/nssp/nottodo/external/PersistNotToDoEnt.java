package com.nssp.nottodo.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class PersistNotToDoEnt {

    private NotToDoRepository repository;

    @Autowired
    public void setRepository(@Qualifier("nottodo") NotToDoRepository repository) {
        this.repository = repository;
    }

    public NotToDoEnt create(NotToDoEnt type) {
        var retorno =this.repository.save(type);
        return retorno;
    }

    public List<NotToDoEnt> listAll() {
        var retorno = this.repository.findAll();
        return StreamSupport.stream(retorno.spliterator(), false).collect(Collectors.toList());
    }
    public Optional<NotToDoEnt> findById(Long id) {
        return this.repository.findById(id);
    }

    public Boolean update(NotToDoEnt type) {
        var retorno = Optional.ofNullable(this.repository.save(type));
        return retorno.isPresent();
    }
}
