package com.gifkrieg.model;

import com.gifkrieg.constants.Defaults;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Rob on 4/2/2017.
 */

@Entity
@Table(name = "user")
public class User {


    private int id;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private State active;
    private int inventorySize;
    private String passwordConfirm;
    private Set<Role> roles;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public State getActive() {
        return active;
    }

    public void setActive(State active) {
        this.active = active;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Column(name = "inventorySize", columnDefinition="INT NOT NULL DEFAULT " + Defaults.INV_START_SIZE)
    public int getInventorySize() {
        return inventorySize;
    }

    public void setInventorySize(int inventorySize) {
        this.inventorySize = inventorySize;
    }
}