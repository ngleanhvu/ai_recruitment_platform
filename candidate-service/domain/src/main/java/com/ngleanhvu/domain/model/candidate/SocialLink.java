package com.ngleanhvu.domain.model.candidate;

import lombok.Builder;

@Builder
public record SocialLink(String type, String url) {}
