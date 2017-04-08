package com.gifkrieg.data;

import com.gifkrieg.model.Score;
import com.gifkrieg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Rob on 4/2/2017.
 */

@Repository("statsRepository")
public interface StatsRepository extends JpaRepository<Score, Long> {

    @Query(value="SELECT * FROM gifkrieg.stats ORDER BY score DESC Limit 0, 20", nativeQuery=true)
    public List<Score> getTopScores();

}