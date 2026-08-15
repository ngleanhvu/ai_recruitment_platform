package com.ngleanhvu.domain.model.application;


import com.ngleanhvu.common.exception.ValidationException;
import com.ngleanhvu.common.util.ValidationUtil;

import java.util.UUID;

public record ApplicationId (
        String value
) {
    public ApplicationId {
        if (ValidationUtil.isEmpty(value)) throw new ValidationException("Application id must not be empty");
    }

    public static ApplicationId generate() {
        return new ApplicationId(UUID.randomUUID().toString());
    }
}
