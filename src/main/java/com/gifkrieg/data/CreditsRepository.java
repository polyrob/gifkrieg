package com.gifkrieg.data;

import com.gifkrieg.model.Credits;
import com.gifkrieg.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Rob on 4/2/2017.
 */

@Repository("creditsRepository")
public interface CreditsRepository extends JpaRepository<Credits, Long> {

    Credits findByUserId(int userId);

    @Modifying
    @Transactional
    @Query(value="UPDATE gifkrieg.credits SET credits = credits + ?2 WHERE user_id = ?1", nativeQuery=true)
    void addCreditsForUser(int userId, int credits);

    @Modifying
    @Transactional
    @Query(value="UPDATE gifkrieg.credits SET credits = credits - ?2 WHERE user_id = ?1", nativeQuery=true)
    void subtractCreditsForUser(int userId, int credits);

}