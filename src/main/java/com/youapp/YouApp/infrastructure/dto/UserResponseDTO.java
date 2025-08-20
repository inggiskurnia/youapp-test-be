package com.youapp.YouApp.infrastructure.dto;

import com.youapp.YouApp.entity.Interest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    @NotNull
    private String name;

    private OffsetDateTime birthday;
    private BigDecimal height;
    private BigDecimal wight;
    private List<Interest> interest;
}
