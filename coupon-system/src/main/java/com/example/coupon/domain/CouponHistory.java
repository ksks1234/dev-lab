package com.example.coupon.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class CouponHistory {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
            , generator = "history_seq_gen")
    @SequenceGenerator(
            name = "history_seq_gen"
            , sequenceName = "history_seq"
            , allocationSize = 1
    )
    private Long historyId;

    @ManyToOne
    @JoinColumn(name = "userId", unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id", unique = true)
    private Coupon coupon;

    private LocalDateTime requestTime;

}
