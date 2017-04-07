package com.gifkrieg.data;


import com.gifkrieg.model.Challenge;
import com.gifkrieg.model.Role;
import com.gifkrieg.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Rob on 4/2/2017.
 */

@Repository("challengeRepository")
public interface ChallengeRepository extends JpaRepository<Challenge, Integer> {

    Challenge findByState(State state);

    List<Challenge> findByIdIn(Collection<Integer> idList);



}