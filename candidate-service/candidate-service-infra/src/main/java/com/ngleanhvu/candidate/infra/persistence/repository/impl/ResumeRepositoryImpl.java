package com.ngleanhvu.candidate.infra.persistence.repository.impl;

import com.ngleanhvu.candidate.domain.model.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.model.resume.Resume;
import com.ngleanhvu.candidate.domain.model.resume.ResumeId;
import com.ngleanhvu.candidate.domain.repository.ResumeRepository;
import com.ngleanhvu.candidate.infra.persistence.documet.resume.ResumeDocument;
import com.ngleanhvu.candidate.infra.persistence.mapper.ResumeDocumentMapper;
import com.ngleanhvu.candidate.infra.persistence.repository.ResumeMongoRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeRepositoryImpl implements ResumeRepository {

  private final ResumeMongoRepository resumeMongoRepository;
  private final ResumeDocumentMapper resumeDocumentMapper;
  private final MongoTemplate mongoTemplate;

  @Override
  public void save(Resume resume) {
    ResumeDocument document = resumeDocumentMapper.toDocument(resume);
    resumeMongoRepository.save(document);
  }

  @Override
  public Optional<Resume> findById(ResumeId id) {
    return resumeMongoRepository.findById(id.value()).map(resumeDocumentMapper::toDomain);
  }

  @Override
  public List<Resume> findByCandidateId(CandidateId candidateId) {
    return resumeMongoRepository.findByCandidateIdOrderByVersionDesc(candidateId.value()).stream()
        .map(resumeDocumentMapper::toDomain)
        .toList();
  }

  @Override
  public Optional<Resume> findLatestByCandidateId(CandidateId candidateId) {
    return resumeMongoRepository
        .findFirstByCandidateIdOrderByVersionDesc(candidateId.value())
        .map(resumeDocumentMapper::toDomain);
  }

  @Override
  public Integer getNextVersion(CandidateId candidateId) {
    return resumeMongoRepository.countByCandidateId(candidateId.value());
  }
}
