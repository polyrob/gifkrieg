package com.gifkrieg.model;

import javax.persistence.*;

/**
 * Created by robbie on 4/5/17.
 */

@Entity
@Table(name = "challenge")
public class Challenge {

    private int id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
