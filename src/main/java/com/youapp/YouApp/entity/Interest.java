package com.youapp.YouApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "interests")
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "interest_id_gen")
    @SequenceGenerator(name = "interest_id_gen", sequenceName = "interest_id_seq", allocationSize = 1)
    @Column(name = "interest_id", nullable = false)
    private Long interestId;

    @NotNull
    @Column(name = "interest_name", nullable = false)
    private String interestName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
