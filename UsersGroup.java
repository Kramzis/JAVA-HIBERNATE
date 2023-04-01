package com.lg;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UsersGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private List<User> users = new ArrayList<>();

    public UsersGroup(Long id, List<User> users) {
        this.id = id;
        this.users = users;
    }

    public UsersGroup(){};

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
