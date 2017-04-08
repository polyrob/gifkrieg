package com.gifkrieg.service;

import com.gifkrieg.data.ChallengeRepository;
import com.gifkrieg.data.GifRepository;
import com.gifkrieg.model.Challenge;
import com.gifkrieg.model.Gif;
import com.gifkrieg.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


}
