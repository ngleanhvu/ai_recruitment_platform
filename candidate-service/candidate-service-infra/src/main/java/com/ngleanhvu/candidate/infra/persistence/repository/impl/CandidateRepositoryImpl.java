package com.ngleanhvu.candidate.infra.persistence.repository.impl;

import com.ngleanhvu.candidate.application.port.output.candidate.CandidateRepository;
import com.ngleanhvu.candidate.domain.candidate.Candidate;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.candidate.Email;
import com.ngleanhvu.candidate.infra.persistence.documet.candidate.CandidateDocument;
import com.ngleanhvu.candidate.infra.persistence.mapper.CandidateDocumentMapper;
import com.ngleanhvu.candidate.infra.persistence.repository.CandidateMongoRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CandidateRepositoryImpl implements CandidateRepository {

  private final CandidateMongoRepository candidateMongoRepository;
  private final CandidateDocumentMapper candidateDocumentMapper;

  public CandidateRepositoryImpl(CandidateMongoRepository candidateMongoRepository,
                                 CandidateDocumentMapper candidateDocumentMapper) {
    this.candidateMongoRepository = candidateMongoRepository;
    this.candidateDocumentMapper = candidateDocumentMapper;
  }

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

  @Override
  public boolean existById(CandidateId candidateId) {
    return candidateMongoRepository.existsById(candidateId.value());
  }
}
