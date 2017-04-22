package com.gifkrieg.service;

import com.gifkrieg.data.GifRepository;
import com.gifkrieg.data.UserGifRepository;
import com.gifkrieg.model.Gif;
import com.gifkrieg.model.UserGif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by robbie on 4/6/17.
 */
@Service("userGifService")
public class UserGifServiceImpl implements UserGifService {

    @Autowired
    private UserGifRepository userGifRepository;

    @Autowired
    private GifRepository gifRepository;

    private Random random = new Random(System.currentTimeMillis());


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

    @Override
    public Gif getNewRandomGif(int userId, List<UserGif> currentInventory) {
        List<Integer> ids = currentInventory.stream().map(UserGif::getGifId).collect(Collectors.toList());
        int count = (int) gifRepository.count();
        assert count > currentInventory.size();     // sanity, just so we don't loop forever
        while (true) {
            int x = random.nextInt(count);
            if (ids.contains(x)) continue;
            // now we have a new random one, add it and return it.
            UserGif newUserGif = new UserGif(userId, x);
            userGifRepository.save(newUserGif);
            return gifRepository.findOne(x);
        }
    }


}
