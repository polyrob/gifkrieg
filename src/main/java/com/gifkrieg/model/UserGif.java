package com.gifkrieg.model;

import javax.persistence.*;

/**
 * Created by robbie on 4/7/17.
 */
@Entity
@Table(name = "user_gif")
public class UserGif {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    private int gifId;

    public UserGif() {
    }

    public UserGif(int userId, int gifId) {
        this.userId = userId;
        this.gifId = gifId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGifId() {
        return gifId;
    }

    public void setGifId(int gifId) {
        this.gifId = gifId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
