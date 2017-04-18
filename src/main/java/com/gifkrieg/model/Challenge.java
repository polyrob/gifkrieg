package com.gifkrieg.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by robbie on 4/5/17.
 */

@Entity
@Table(name = "challenge")
public class Challenge implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Enumerated(EnumType.ORDINAL)
    private State state;
    private String cardText;
    private long startTime;
    private long endTime;

    @Transient
    private int submissions;
    @Transient
    private int votes;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getCardText() {
        return cardText;
    }

    public void setCardText(String cardText) {
        this.cardText = cardText;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getSubmissions() {
        return submissions;
    }

    public void setSubmissions(int submissions) {
        this.submissions = submissions;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
