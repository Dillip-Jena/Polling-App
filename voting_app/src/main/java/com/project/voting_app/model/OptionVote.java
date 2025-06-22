package com.project.voting_app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class OptionVote {
  @Column(name = "option_text")
  private String optionText;
  @Column(name = "vote_count")
  private Long voteCount = 0L;
}
