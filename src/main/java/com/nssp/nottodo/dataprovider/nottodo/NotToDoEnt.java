package com.nssp.nottodo.dataprovider.nottodo;

import com.nssp.nottodo.dataprovider.user.UserEnt;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "nottodo")
public class NotToDoEnt {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "item_name")
    private String itemName;
    private String description;
    private String date;
    private boolean enabled;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEnt userEnt;
    @Column(name = "update_date")
    private String updateDate;

    public NotToDoEnt(long id, String itemName, String description, boolean enabled, String date, UserEnt userEnt) {
        this.id = id;
        this.itemName = itemName;
        this.description = description;
        this.enabled = enabled;
        this.date = date;
        this.userEnt = userEnt;
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

    public Long getId() {
        return id;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setUserEnt(UserEnt userEnt) {
        this.userEnt = userEnt;
    }

    public UserEnt getUserEnt() {
        return userEnt;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

}
