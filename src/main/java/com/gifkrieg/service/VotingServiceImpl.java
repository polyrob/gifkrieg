package com.gifkrieg.service;

import com.gifkrieg.constants.Defaults;
import com.gifkrieg.data.ChallengeRepository;
import com.gifkrieg.data.SubmissionRepository;
import com.gifkrieg.data.VotingRepository;
import com.gifkrieg.model.Challenge;
import com.gifkrieg.model.State;
import com.gifkrieg.model.Submission;
import com.gifkrieg.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by robbie on 4/10/17.
 */
@Service("votingService")
public class VotingServiceImpl implements VotingService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private VotingRepository votingRepository;

    @Autowired
    private StringRedisTemplate template;


    public boolean hasAlreadyVotedForVotingRound(int userId) {
        Challenge currentChallenge = challengeRepository.findByState(State.VOTING);

        if (votingRepository.existsByChallengeIdAndVoterId(currentChallenge.getId(), userId)) {
            return true;
        }
        return false;
    }

    @Override
    public int getVotesForChallenge(Challenge voting) {
        return votingRepository.countByChallengeId(voting.getId());
    }


    @Override
    public List<Submission> getVotableEntriesForChallenge(int challengeId, int userId) {
        // check if user has already requested votable entries
        String key = getUserChallengeCacheKey(userId, challengeId);
        List<String> cacheResult = template.opsForList().range(key,0,-1);
        if (cacheResult != null && cacheResult.size() > 0) {
            // already cached, so return these submissions
            return submissionRepository.findAllByChallengeIdAndIdIn(challengeId, entriesAsListOfInt(cacheResult));
        }

        List<Submission> entries =  submissionRepository.getRandomSubmissions(challengeId, userId, Defaults.ENTRIES_TO_VOTE);

        // cache so user can only vote on this options if they come back before voting
        template.opsForList().leftPushAll(key, entriesAsListOfStrings(entries));
        template.expire(key, 1, TimeUnit.DAYS);

        return entries;
    }

    private String getUserChallengeCacheKey(int userId, int challengeId) {
        return "vote:" + userId + ":" + challengeId;
    }

    private List<String> entriesAsListOfStrings(List<Submission> entries) {
        return entries.stream().map(s -> String.valueOf(s.getGif().getId())).collect(Collectors.toList());
    }

    private List<Integer> entriesAsListOfInt(List<String> entries) {
        return entries.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    public void castVoteForSubmission(int challengeId, int gifId, int voterId) {
        Submission s = submissionRepository.findOneByChallengeIdAndGifId(challengeId, gifId);
        if (s == null) {
            throw new NoSuchElementException("Submission was not found. challenge: " + challengeId + ", gifId: " + gifId);
        }

        Vote v = new Vote(challengeId, voterId, s.getUserId(), gifId);
        votingRepository.save(v);
    }

    @Override
    public boolean hasUserVotedInRound(int userId, int challengeId) {
        return votingRepository.existsByChallengeIdAndVoterId(challengeId, userId);
    }

}
