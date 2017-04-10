package com.gifkrieg.model;

import com.gifkrieg.constants.Defaults;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by robbie on 4/9/17.
 */
@Entity
@Table(name = "submission")
public class Submission {

    private int id;
    private int challengeId;
    private int userId;
    private int gifId;
    private int votes;

    public Submission() {
    }

    public Submission(int challengeId, int gifId, int userId) {
        this.challengeId = challengeId;
        this.gifId = gifId;
        this.userId = userId;
    }

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

    public int getGifId() {
        return gifId;
    }

    public void setGifId(int gifId) {
        this.gifId = gifId;
    }

    @Column(columnDefinition="INT NOT NULL DEFAULT 0")
    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
