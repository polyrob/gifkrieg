package com.gifkrieg.model;

/**
 * Created by robbie on 4/17/17.
 */
public class UserState {

    private int credits;
    private boolean hasSubmittedCurrent;
    private boolean hasVotedCurrent;


    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
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
