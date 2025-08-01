package com.example.coupon.repository;

import com.example.coupon.domain.Coupon;
import com.example.coupon.domain.CouponStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findFirstByStatus(CouponStatus status);
}
