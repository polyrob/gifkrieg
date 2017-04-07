package com.gifkrieg.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by robbie on 4/5/17.
 */

@Entity
@Table(name = "challenge")
public class Challenge {

    private int id;
    @Enumerated(EnumType.ORDINAL)
    private State state;
    private String cardText;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
