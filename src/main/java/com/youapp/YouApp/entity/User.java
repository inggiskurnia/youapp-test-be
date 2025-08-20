package com.youapp.YouApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_gen")
    @SequenceGenerator(name = "user_id_gen", sequenceName = "user_id_seq", allocationSize = 1)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Email
    @NotNull
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String username;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    private String name;

    private OffsetDateTime birthday;

    private BigDecimal weight;

    private BigDecimal height;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Interest> interests = new ArrayList<>();

    public void addInterest(Interest interest) {
        interests.add(interest);
        interest.setUser(this);
    }

    public void removeInterest(Interest interest) {
        interests.remove(interest);
        interest.setUser(null);
    }
}
