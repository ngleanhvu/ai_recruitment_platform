package com.ngleanhvu.domain.model.candidate;

import lombok.Builder;

@Builder
public record Address (
        String address,
        String district,
        String country,
        String city
) {
}
