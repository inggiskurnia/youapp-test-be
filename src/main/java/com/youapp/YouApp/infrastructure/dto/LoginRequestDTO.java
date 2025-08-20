package com.youapp.YouApp.infrastructure.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    @NotNull
    @Email
    private Long email;

    @NotNull
    private String username;

    @NotNull
    private String password;
}
