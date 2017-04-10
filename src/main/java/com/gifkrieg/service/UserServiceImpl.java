package com.gifkrieg.service;


import com.gifkrieg.constants.Defaults;
import com.gifkrieg.data.GifRepository;
import com.gifkrieg.data.RoleRepository;
import com.gifkrieg.data.UserGifRepository;
import com.gifkrieg.data.UserRepository;
import com.gifkrieg.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

/**
 * Created by Rob on 4/2/2017.
 */

@Service("userService")
public class UserServiceImpl implements UserService {
    Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private GifRepository gifRepository;
    @Autowired
    private UserGifRepository userGifRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    Random random = new Random(System.currentTimeMillis());


    @Override
    public void saveNewUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        user.setActive(State.ACTIVE);
        userRepository.save(user);
    }

    @Override
    public void acquireStarterGifs(User user) {
        // TODO? validate has no gifs, just for sanity
        int count = (int) gifRepository.count();
        assert count > 5;   // just to make sure we don't loop forever

        //TODO: maybe get list of least used gifs to choose from
        List<Integer> intList = new ArrayList<>(Defaults.INV_START_SIZE);
        while (intList.size() < Defaults.INV_START_SIZE) {
            int x = random.nextInt(count);
            if (intList.contains(x)) continue;
            intList.add(x);
        }

        // now we have unique starter gif indices.
        List<Gif> gifs = gifRepository.findByIdIn(intList);

        List<UserGif> userGifs = new ArrayList<>();
        for (Gif gif: gifs) {
            userGifs.add(new UserGif(user.getId(), gif.getId()));
        }
        userGifRepository.save(userGifs);

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}