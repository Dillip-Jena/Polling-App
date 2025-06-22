package com.project.voting_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.voting_app.model.OptionVote;
import com.project.voting_app.model.Poll;
import com.project.voting_app.repository.PollRepository;
import com.project.voting_app.request.Vote;

@Service
public class PollService {

  private PollRepository pollRepository;

  public PollService(PollRepository pollRepository){
    this.pollRepository = pollRepository;
  }

  public Poll createPoll(Poll poll) {
    return pollRepository.save(poll);
  }

  public List<Poll> getAllPolls() {
    return pollRepository.findAll();
  }

  public Optional<Poll> getPollById(Long id) {
    return pollRepository.findById(id);
  }

  public void vote(Vote vote) {
    Poll poll = pollRepository.findById(vote.getPollId())
                .orElseThrow(() -> new RuntimeException("Poll not found."));

    //get all the options
    List<OptionVote> options = poll.getOptionVotes();

    //check if the options are not greater than the available options
    int optionIndex = vote.getOptionIndex();
    if(optionIndex < 0 || optionIndex >= options.size()){
      throw new IllegalArgumentException("Invalid option index.");
    }

    //get the selected option
    OptionVote selectedOption = options.get(optionIndex);

    //increment vote for the selected option
    selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);

    //now save the incremented votecount into the database
    pollRepository.save(poll);
  }

}
