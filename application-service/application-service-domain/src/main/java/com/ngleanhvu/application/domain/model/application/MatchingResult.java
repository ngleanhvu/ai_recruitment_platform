package com.ngleanhvu.application.domain.model.application;

import java.math.BigDecimal;
import java.util.List;

public record MatchingResult(
    BigDecimal score,
    List<String> matchedSkills,
    List<String> missingSkills,
    String explanation,
    Integer experience) {}
