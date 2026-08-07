package com.ngleanhvu.infra.persistence.repository.impl;

import com.ngleanhvu.domain.model.candidate.Candidate;
import com.ngleanhvu.domain.repository.CandidateRepository;
import com.ngleanhvu.infra.persistence.documet.candidate.CandidateDocument;
import com.ngleanhvu.infra.persistence.mapper.CandidateDocumentMapper;
import com.ngleanhvu.infra.persistence.repository.CandidateMongoRepository;
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
    public void createCandidate(Candidate candidate) {
        CandidateDocument candidateDocument = candidateDocumentMapper.toDocument(candidate);
        candidateMongoRepository.save(candidateDocument);
    }

    @Override
    public Candidate getCandidateDetail(String candidateId) {
        CandidateDocument candidateDocument = candidateMongoRepository.findById(candidateId).orElse(null);
        return candidateDocumentMapper.toDomain(candidateDocument);
    }
}
