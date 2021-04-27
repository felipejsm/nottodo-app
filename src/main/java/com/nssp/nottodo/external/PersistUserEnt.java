package com.nssp.nottodo.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class PersistUserEnt {
    private UserRepository repository;
    private NotToDoRepository notToDoRepository;

    @Autowired
    public void setNotToDoRepository(@Qualifier("nottodo") NotToDoRepository notToDoRepository) {
        this.notToDoRepository = notToDoRepository;
    }

    @Autowired
    public void setRepository(@Qualifier("user") UserRepository repository) {
        this.repository = repository;
    }
    public UserEnt create(UserEnt type) {
        //first save the child
        var notToDo = type.getNotToDoEntList();
        type.setNotToDoEntList(null);
        var retorno = this.repository.save(type);
        for(NotToDoEnt not : notToDo) {
            retorno.setNotToDoEntList(not);
        }
        return this.repository.save(retorno);
    }

    public List<UserEnt> listAll() {
        var retorno = this.repository.findAll();
        return StreamSupport.stream(retorno.spliterator(), false).collect(Collectors.toList());
    }

    public Optional<UserEnt> findById(Long id) {
        return this.repository.findById(id);
    }

    public Boolean update(UserEnt type) {
        var retorno = Optional.ofNullable(this.repository.save(type));
        return retorno.isPresent();
    }

}
