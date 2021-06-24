package com.nssp.nottodo.dataprovider.user;

import com.nssp.nottodo.dataprovider.nottodo.NotToDoEnt;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "user")
public class UserEnt {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String name;
    private String email;
    private String nick;
    @OneToMany(
            //cascade = CascadeType.,
            orphanRemoval = true
    )
    private Set<NotToDoEnt> notToDoEntList = new HashSet<>();
    private boolean enabled;
    public UserEnt(String id, String name, String email, String nick, NotToDoEnt notToDoId, boolean enabled) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nick = nick;
        this.notToDoEntList.add(notToDoId);
        this.enabled = enabled;
    }
    public UserEnt() {}

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
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
