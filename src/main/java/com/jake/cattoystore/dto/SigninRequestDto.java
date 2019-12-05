package com.jake.cattoystore.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SigninRequestDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
