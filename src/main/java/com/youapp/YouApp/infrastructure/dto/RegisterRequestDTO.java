package com.youapp.YouApp.infrastructure.dto;

import com.youapp.YouApp.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String username;

    @NotNull
    private String password;

    public User toEntity(){
        User user = new User();
        user.setEmail(this.email);
        user.setUsername(this.username);
        user.setPassword(this.password);
        return user;
    }
}
