package com.example.coupon.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class CouponHistory {
    private Long historyId;

    @ManyToMany
    private User user;
    private Coupon coupon;
    private LocalDateTime requestTime;

}
