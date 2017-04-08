package com.gifkrieg.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by robbie on 4/7/17.
 */
@Entity
@Table(name = "stats")
public class Score implements Serializable {

    private int id;
    private String username;
    private int score;
    private int rounds;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }
}
