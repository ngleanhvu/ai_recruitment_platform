package com.ngleanhvu.domain.repository;

import com.ngleanhvu.domain.model.candidate.Candidate;
import com.ngleanhvu.domain.model.candidate.CandidateId;
import com.ngleanhvu.domain.model.candidate.Email;
import java.util.Optional;

public interface CandidateRepository {
  void save(Candidate candidate);

  Optional<Candidate> findById(CandidateId candidateId);

  boolean existByEmail(Email email);

  Optional<Candidate> findByEmail(Email email);

  boolean existByPhone(String phone);
}
