package com.ngleanhvu.candidate.domain.candidate;

import lombok.Builder;

@Builder
public record Address(String address, String district, String country, String city) {}
