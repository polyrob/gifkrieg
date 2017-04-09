package com.gifkrieg.service;

import com.gifkrieg.data.UserGifRepository;
import com.gifkrieg.model.UserGif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by robbie on 4/6/17.
 */
@Service("userGifService")
public class UserGifServiceImpl implements UserGifService {

    @Autowired
    private UserGifRepository userGifRepository;


    @Override
    //@Cacheable(value = "userGifs")
    public List<UserGif> getUserGifs(int userId) {
        return userGifRepository.findByUserId(userId);
    }

    @Override
    public UserGif getUserGif(int userid, int gifId) {
        return userGifRepository.findByUserIdAndGifId(userid, gifId);
    }


    @Override
    public void removeGifFromInventory(UserGif userGif) {
        userGifRepository.delete(userGif);
    }


}
