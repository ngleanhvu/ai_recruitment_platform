package com.ngleanhvu.domain.repository;

import com.ngleanhvu.domain.model.candidate.Candidate;
import com.ngleanhvu.domain.model.candidate.CandidateId;
import java.util.Optional;

public interface CandidateRepository {
  void save(Candidate candidate);
  Optional<Candidate> findById(CandidateId candidateId);
}
