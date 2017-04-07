package com.gifkrieg.data;

import com.gifkrieg.model.Gif;
import com.gifkrieg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Rob on 4/2/2017.
 */

@Repository("gifRepository")
public interface GifRepository extends JpaRepository<Gif, Long> {
}