package com.gifkrieg.model;

import javax.persistence.*;

/**
 * Created by robbie on 4/9/17.
 */
@Entity
@Table(name = "voting")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int challengeId;
    private int voterId;
    private int ownerId;
    private int gifIdVotedFor;

    public Vote() {
    }

    public Vote(int challengeId, int voterId, int ownerId, int gifIdVotedFor) {
        this.challengeId = challengeId;
        this.voterId = voterId;
        this.ownerId = ownerId;
        this.gifIdVotedFor = gifIdVotedFor;
    }


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

    public int getVoterId() {
        return voterId;
    }

    public void setVoterId(int userId) {
        this.voterId = userId;
    }

    public int getGifIdVotedFor() {
        return gifIdVotedFor;
    }

    public void setGifIdVotedFor(int gifIdVotedFor) {
        this.gifIdVotedFor = gifIdVotedFor;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
