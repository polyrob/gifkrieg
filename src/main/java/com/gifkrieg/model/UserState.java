package com.gifkrieg.model;

/**
 * Created by robbie on 4/17/17.
 */
public class UserState {

    private int points;
    private boolean hasSubmittedCurrent;
    private boolean hasVotedCurrent;


    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isHasSubmittedCurrent() {
        return hasSubmittedCurrent;
    }

    public void setHasSubmittedCurrent(boolean hasSubmittedCurrent) {
        this.hasSubmittedCurrent = hasSubmittedCurrent;
    }

    public boolean isHasVotedCurrent() {
        return hasVotedCurrent;
    }

    public void setHasVotedCurrent(boolean hasVotedCurrent) {
        this.hasVotedCurrent = hasVotedCurrent;
    }
}
