package com.gifkrieg.data;

import com.gifkrieg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Rob on 4/2/2017.
 */

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query(value="UPDATE gifkrieg.user set rounds = rounds + 1 where user = ?1", nativeQuery=true)
    void incrementRounds(int userId);
}