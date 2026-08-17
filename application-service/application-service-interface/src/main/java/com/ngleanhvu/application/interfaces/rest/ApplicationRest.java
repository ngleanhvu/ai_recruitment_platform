package com.ngleanhvu.application.interfaces.rest;

import com.ngleanhvu.application.application.dto.request.CreateApplicationRequest;
import com.ngleanhvu.application.application.port.input.application.CreateApplicationUseCase;
import com.ngleanhvu.common.response.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/applications")
public record ApplicationRest (
        CreateApplicationUseCase createApplicationUseCase
) {
    @PostMapping("/create")
    public ApiResponse<Void> createApplication(@RequestBody CreateApplicationRequest request) {
        createApplicationUseCase.execute(request);
        return ApiResponse.success("Create new application success", null);
    }
}
