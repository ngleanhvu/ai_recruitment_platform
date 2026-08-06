package com.ngleanhvu.infra.persistence.repository.impl;

import com.ngleanhvu.domain.model.candidate.Candidate;
import com.ngleanhvu.domain.repository.CandidateRepository;
import com.ngleanhvu.infra.persistence.documet.candidate.CandidateDocument;
import com.ngleanhvu.infra.persistence.mapper.CandidateDocumentMapper;
import com.ngleanhvu.infra.persistence.repository.CandidateMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateRepositoryImpl implements CandidateRepository {

    private final CandidateMongoRepository candidateMongoRepository;

    private final CandidateDocumentMapper candidateDocumentMapper;

    @Override
    public void createCandidate(Candidate candidate) {
        CandidateDocument candidateDocument = candidateDocumentMapper.toDocument(candidate);
        candidateMongoRepository.save(candidateDocument);
    }
}
