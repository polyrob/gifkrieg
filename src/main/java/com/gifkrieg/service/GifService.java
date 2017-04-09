package com.gifkrieg.service;

import com.gifkrieg.model.Gif;
import com.gifkrieg.model.UserGif;

import java.util.List;

/**
 * Created by robbie on 4/6/17.
 */
public interface GifService {

    public List<Gif> getAllGifs();

    public List<Gif> getGifsForUser(List<UserGif> id);
}
