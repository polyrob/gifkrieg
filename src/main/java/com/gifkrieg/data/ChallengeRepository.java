package com.gifkrieg.data;


import com.gifkrieg.model.Challenge;
import com.gifkrieg.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Rob on 4/2/2017.
 */

@Repository("challengeRepository")
public interface ChallengeRepository extends JpaRepository<Challenge, Integer> {
    //Role findByRole(String role);

}