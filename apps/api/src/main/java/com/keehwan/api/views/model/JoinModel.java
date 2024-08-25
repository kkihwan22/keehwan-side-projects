package com.keehwan.api.views.model;

import jakarta.validation.constraints.NotBlank;

public record JoinModel(
        @NotBlank String username,
        @NotBlank String password) {
}
