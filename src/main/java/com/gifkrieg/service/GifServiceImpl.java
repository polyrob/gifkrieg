package com.gifkrieg.service;

import com.gifkrieg.data.GifRepository;
import com.gifkrieg.model.Gif;
import com.gifkrieg.model.UserGif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by robbie on 4/6/17.
 */
@Service("gifService")
public class GifServiceImpl implements GifService {

    @Autowired
    private GifRepository gifRepository;


    @Override
    public List<Gif> getAllGifs() {
        return gifRepository.findAll();
    }

    @Override
    public List<Gif> getGifsForUser(List<UserGif> userGifList) {
        List<Integer> gifList = userGifList.stream().map(UserGif::getGifId).collect(Collectors.toList());
        return gifRepository.findAllByIdIn(gifList);
    }


}
