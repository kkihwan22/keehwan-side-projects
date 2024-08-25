package com.keehwan.api.rest.dto;

public class JoinDTO {

    public record JoinRequest(
            String email,
            String password
    ) {}
}
