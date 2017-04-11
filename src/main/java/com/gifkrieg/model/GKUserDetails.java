package com.gifkrieg.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by robbie on 4/8/17.
 */
public class GKUserDetails extends org.springframework.security.core.userdetails.User {
    private int userId;
    private List<Gif> userGifs;
    private boolean hasSubmittedCurrent;
    private boolean hasVotedCurrent;

    public GKUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public GKUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Gif> getUserGifs() {
        return userGifs;
    }

    public void setUserGifs(List<Gif> userGifs) {
        this.userGifs = userGifs;
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
