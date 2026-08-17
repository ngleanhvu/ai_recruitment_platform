package com.ngleanhvu.application.infra.persistence.repository.impl;

import com.ngleanhvu.application.infra.persistence.document.application.ApplicationDocument;
import com.ngleanhvu.application.infra.persistence.mapper.ApplicationDocumentMapper;
import com.ngleanhvu.application.infra.persistence.repository.ApplicationMongoRepository;
import com.ngleanhvu.application.domain.model.application.Application;
import com.ngleanhvu.application.domain.model.application.ApplicationId;
import com.ngleanhvu.application.application.port.output.application.ApplicationRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class ApplicationRepositoryImpl implements ApplicationRepository {

  private final ApplicationMongoRepository applicationMongoRepository;
  private final ApplicationDocumentMapper applicationDocumentMapper;

  @Override
  public void save(Application application) {
    ApplicationDocument document = applicationDocumentMapper.toDocument(application);
    applicationMongoRepository.save(document);
  }

  @Override
  public Optional<Application> findById(ApplicationId id) {
    return Optional.empty();
  }
}
