package com.nssp.nottodo.core.entities;

public class NotToDo {
    private String itemName;
    private String description;
    private String date;
    private boolean enabled;
    private User user;

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getItemName() {
        return itemName;
    }

    public User getUser() {
        return user;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
