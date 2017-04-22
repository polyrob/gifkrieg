package com.gifkrieg.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by robbie on 4/7/17.
 */
@Entity
@Table(name = "credits")
public class UserDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;

    @Column(columnDefinition="INT NOT NULL DEFAULT 5")
    private int credits;

    @Column(columnDefinition="INT NOT NULL DEFAULT 3")
    private int invSize;


    public UserDetails() {
    }

    public UserDetails(int userId, int credits) {
        this.userId = userId;
        this.credits = credits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getInvSize() {
        return invSize;
    }

    public void setInvSize(int invSize) {
        this.invSize = invSize;
    }
}
