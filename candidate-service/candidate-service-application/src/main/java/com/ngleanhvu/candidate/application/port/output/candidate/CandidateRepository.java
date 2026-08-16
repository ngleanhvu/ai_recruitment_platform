package com.ngleanhvu.candidate.application.port.output.candidate;

import com.ngleanhvu.candidate.domain.candidate.Candidate;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.candidate.Email;
import java.util.Optional;

public interface CandidateRepository {
  void save(Candidate candidate);

  Optional<Candidate> findById(CandidateId candidateId);

  boolean existByEmail(Email email);

  Optional<Candidate> findByEmail(Email email);

  boolean existByPhone(String phone);

  boolean existById(CandidateId candidateId);
}
