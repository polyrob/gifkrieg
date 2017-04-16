package com.gifkrieg.model;

import javax.persistence.*;

/**
 * Created by robbie on 4/9/17.
 */
@Entity
@Table(name = "submission")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int challengeId;
    private int userId;
    @Column(columnDefinition="INT NOT NULL DEFAULT 0")
    private int votes;

    @OneToOne
    @JoinColumn(name = "gif_id")
    private Gif gif;

    public Submission() {
    }

    public Submission(int challengeId, int gifId, int userId) {
        this.challengeId = challengeId;
//        this.gifId = gifId;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public Gif getGif() {
        return gif;
    }

    public void setGif(Gif gif) {
        this.gif = gif;
    }
}
