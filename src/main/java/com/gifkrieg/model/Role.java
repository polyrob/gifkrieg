package com.gifkrieg.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Rob on 4/2/2017.
 */

@Entity
@Table(name = "role")
public class Role {

    private int id;
    private String role;
    private Set<User> users;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @ManyToMany(mappedBy = "roles")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


}