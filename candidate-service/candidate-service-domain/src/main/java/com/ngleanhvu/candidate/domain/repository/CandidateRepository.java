package com.ngleanhvu.candidate.domain.repository;

import com.ngleanhvu.candidate.domain.model.candidate.Candidate;
import com.ngleanhvu.candidate.domain.model.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.model.candidate.Email;
import java.util.Optional;

public interface CandidateRepository {
  void save(Candidate candidate);

  Optional<Candidate> findById(CandidateId candidateId);

  boolean existByEmail(Email email);

  Optional<Candidate> findByEmail(Email email);

  boolean existByPhone(String phone);
}
