package com.ngleanhvu.domain.repository;

import com.ngleanhvu.domain.model.application.Application;
import com.ngleanhvu.domain.model.application.ApplicationId;

import java.util.Optional;

public interface ApplicationRepository {
    void save(Application application);
    Optional<Application> findById(ApplicationId id);
}
