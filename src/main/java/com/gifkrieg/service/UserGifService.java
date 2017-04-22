package com.gifkrieg.service;

import com.gifkrieg.model.Gif;
import com.gifkrieg.model.UserGif;

import java.util.List;

/**
 * Created by robbie on 4/6/17.
 */
public interface UserGifService {


    List<UserGif> getUserGifs(int userId);

    UserGif getUserGif(int userid, int gifId);

    void removeGifFromInventory(UserGif userGif);

    Gif getNewRandomGif(int userId, List<UserGif> currentInventory);
}
