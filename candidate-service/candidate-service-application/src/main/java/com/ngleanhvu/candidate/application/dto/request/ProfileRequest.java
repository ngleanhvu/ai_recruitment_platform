package com.ngleanhvu.candidate.application.dto.request;

public record ProfileRequest(
    String firstName, String lastName, String email, String phone, String summary) {}
