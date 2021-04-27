package com.nssp.nottodo.external;

import javax.persistence.Table;

@Table(name = "nottodo")
public class NotToDoEnt {
    private Long id;
    private String itemName;
    private String description;
    private String date;
    private UserEnt user;
    private boolean enabled;

    public NotToDoEnt(long id, String itemName, String description, boolean enabled, String date) {
        this.id = id;
        this.itemName = itemName;
        this.description = description;
        this.enabled = enabled;
        this.date = date;
    }
    public NotToDoEnt(){}

    public void setId(Long id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setUser(UserEnt user) {
        this.user = user;
    }

    public UserEnt getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
