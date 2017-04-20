package com.gifkrieg.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by robbie on 4/7/17.
 */
@Entity
@Table(name = "credits")
public class Credits implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(columnDefinition="INT NOT NULL DEFAULT 0")
    private int credits;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
