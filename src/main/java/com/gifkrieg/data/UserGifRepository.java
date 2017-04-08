package com.gifkrieg.data;

import com.gifkrieg.model.UserGif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Rob on 4/2/2017.
 */

@Repository("userGifRepository")
public interface UserGifRepository extends JpaRepository<UserGif, Long> {

}