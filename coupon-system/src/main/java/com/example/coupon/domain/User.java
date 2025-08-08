package com.example.coupon.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
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

    public User(String name, String mail) {
    }
}