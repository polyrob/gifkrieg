package com.gifkrieg.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by robbie on 4/5/17.
 */

@Entity
@Table(name = "gif")
public class Gif implements Serializable {

    private int id;
    @Column(length = 128)
    private String url;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
