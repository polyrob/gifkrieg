package com.gifkrieg.data;


import com.gifkrieg.model.Challenge;
import com.gifkrieg.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Rob on 4/2/2017.
 */

@Repository("submissionRepository")
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {

    @Query(value="SELECT * from submission where challenge_id = ?1 AND user_id != ?2 ORDER BY RAND() LIMIT ?3", nativeQuery=true)
    List<Submission> getRandomSubmissions(int challengeId, int userId, int count);


    @Query(value="SELECT 1 from submission where challenge_id = ?1 AND user_id != ?2", nativeQuery=true)
    boolean hasUserSubmittedChallenge(int challengeId, int userId);

    boolean existsByChallengeIdAndUserId(int challengeId, int userId);

    int countByChallengeId(int challengeId);
}