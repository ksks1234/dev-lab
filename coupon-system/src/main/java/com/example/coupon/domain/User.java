package com.example.coupon.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
            , generator = "user_seq_gen")
    @SequenceGenerator(
            name = "user_seq_gen"
            , sequenceName = "user_seq"
            , allocationSize = 1
    )
    private Long userId;
    private String name;
    private String email;
}