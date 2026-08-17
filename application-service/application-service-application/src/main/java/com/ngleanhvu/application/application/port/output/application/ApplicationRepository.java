package com.ngleanhvu.application.application.port.output.application;

import com.ngleanhvu.application.domain.model.application.Application;
import com.ngleanhvu.application.domain.model.application.ApplicationId;
import java.util.Optional;

public interface ApplicationRepository {
  void save(Application application);
  Optional<Application> findById(ApplicationId id);
}
