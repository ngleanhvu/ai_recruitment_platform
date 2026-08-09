package com.ngleanhvu.infra.persistence.repository.impl;

import com.ngleanhvu.domain.model.candidate.Candidate;
import com.ngleanhvu.domain.model.candidate.CandidateId;
import com.ngleanhvu.domain.model.candidate.Email;
import com.ngleanhvu.domain.repository.CandidateRepository;
import com.ngleanhvu.infra.persistence.documet.candidate.CandidateDocument;
import com.ngleanhvu.infra.persistence.mapper.CandidateDocumentMapper;
import com.ngleanhvu.infra.persistence.repository.CandidateMongoRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CandidateRepositoryImpl implements CandidateRepository {

  private final CandidateMongoRepository candidateMongoRepository;
  private final CandidateDocumentMapper candidateDocumentMapper;

  @Override
  public void save(Candidate candidate) {
    CandidateDocument candidateDocument = candidateDocumentMapper.toDocument(candidate);
    candidateMongoRepository.save(candidateDocument);
  }

  @Override
  public Optional<Candidate> findById(CandidateId candidateId) {
    return candidateMongoRepository
        .findById(candidateId.value())
        .map(candidateDocumentMapper::toDomain);
  }

  @Override
  public boolean existByEmail(Email email) {
    return candidateMongoRepository.existsByEmail(email.value());
  }

  @Override
  public Optional<Candidate> findByEmail(Email email) {
    return candidateMongoRepository
        .findByEmail(email.value())
        .map(candidateDocumentMapper::toDomain);
  }

  @Override
  public boolean existByPhone(String phone) {
    return candidateMongoRepository.existsByPhone(phone);
  }
}
