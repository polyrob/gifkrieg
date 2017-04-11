package com.gifkrieg.model;

import javax.persistence.*;

/**
 * Created by robbie on 4/9/17.
 */
@Entity
@Table(name = "voting")
public class Vote {

    private int id;
    private int challengeId;
    private int userId;
    private int gifIdVotedFor;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGifIdVotedFor() {
        return gifIdVotedFor;
    }

    public void setGifIdVotedFor(int gifIdVotedFor) {
        this.gifIdVotedFor = gifIdVotedFor;
    }
}
