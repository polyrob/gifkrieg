package com.gifkrieg.data;

import com.gifkrieg.model.Score;
import com.gifkrieg.model.UserGif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Rob on 4/2/2017.
 */

@Repository("userGifRepository")
public interface UserGifRepository extends JpaRepository<UserGif, Long> {

    List<UserGif> findByUserId(int userId);

    UserGif findByUserIdAndGifId(int userId, int gifId);

    @Modifying
    @Transactional
    void deleteByUserIdAndGifId(int userId, int gifId);
}