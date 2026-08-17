package com.ngleanhvu.application.application.port.input.application;

import com.ngleanhvu.application.application.dto.request.CreateApplicationRequest;

public interface CreateApplicationUseCase {
    void execute(CreateApplicationRequest request);
}
