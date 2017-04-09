package com.gifkrieg.data;


import com.gifkrieg.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Rob on 4/2/2017.
 */

@Repository("submissionRepository")
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {

}