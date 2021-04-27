package com.nssp.nottodo.external;

import org.springframework.data.relational.core.mapping.MappedCollection;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Table(name = "user")
public class UserEnt {

    @Id
    private Long id;
    private String name;
    private String email;
    private String nick;
    @MappedCollection(idColumn = "nottodo_id")
    private Set<NotToDoEnt> notToDoEntList = new HashSet<>();
    private boolean enabled;
    public UserEnt(Long id, String name, String email, String nick, NotToDoEnt notToDoId, boolean enabled) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nick = nick;
        this.notToDoEntList.add(notToDoId);
        this.enabled = enabled;
    }
    public UserEnt() {}

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setNotToDoEntList(NotToDoEnt notToDoEnt) {
        this.notToDoEntList.add(notToDoEnt);
    }

    public Set<NotToDoEnt> getNotToDoEntList() {
        return notToDoEntList;
    }
}
