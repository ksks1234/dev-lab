package com.example.coupon.repository;

import com.example.coupon.domain.CouponHistory;
import com.example.coupon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponHistoryRepository extends JpaRepository<CouponHistory, Long> {
}
