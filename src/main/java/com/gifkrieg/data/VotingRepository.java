package com.gifkrieg.data;


import com.gifkrieg.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Created by Rob on 4/2/2017.
 */

@Repository("votingRepository")
public interface VotingRepository extends JpaRepository<Vote, Integer> {

    @Query(value="SELECT 1 from voting where challenge_id = ?1 AND user_id != ?2", nativeQuery=true)
    boolean hasUserVotedInChallenge(int challengeId, int userId);

    boolean existsByChallengeIdAndUserId(int challengeId, int userId);

    int countByChallengeId(int challengeId);
}