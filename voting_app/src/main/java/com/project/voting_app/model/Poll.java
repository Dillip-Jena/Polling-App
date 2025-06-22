package com.project.voting_app.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Poll {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String question;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "poll_option_votes", joinColumns = @JoinColumn(name = "poll_id"))
  private List<OptionVote> optionVotes = new ArrayList<>();
}
