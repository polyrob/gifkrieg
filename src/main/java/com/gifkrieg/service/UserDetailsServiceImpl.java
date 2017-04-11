package com.gifkrieg.service;

import com.gifkrieg.data.UserRepository;
import com.gifkrieg.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by robbie on 4/4/17.
 */


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGifService userGifService;

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private GifService gifService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            // signon failed because the username was not found
            throw new UsernameNotFoundException(username);
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        GKUserDetails ud = new GKUserDetails(user.getUsername(), user.getPassword(), grantedAuthorities);
        ud.setUserId(user.getId());

        ////////// set anything else we want available to client on login
        // get user's gifs
        List<UserGif> userGifs = userGifService.getUserGifs(user.getId());
        List<Gif> gifs = gifService.getGifsForUser(userGifs);
        ud.setUserGifs(gifs);

        // determine if the user can submit current round
        boolean hasSubmitted = submissionService.hasAlreadySubmittedForCurrentRound(ud.getUserId());
        ud.setHasSubmittedCurrent(hasSubmitted);
        // determine if user can vote for voting round
        boolean hasVoted = submissionService.hasAlreadyVotedForVotingRound(ud.getUserId());
        ud.setHasVotedCurrent(hasVoted);
        ////////

        return ud;
    }
}