package com.example.coupon.service;

import com.example.coupon.domain.Coupon;
import com.example.coupon.domain.CouponStatus;
import com.example.coupon.dto.CouponResponseDto;
import com.example.coupon.dto.IssuedResultDto;
import com.example.coupon.repository.CouponRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CouponService {
    private final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Transactional
    public IssuedResultDto issueCoupon() {
        try {
            Coupon coupon = couponRepository.findFirstByStatus(CouponStatus.UNUSED)
                            .orElseThrow(() -> new RuntimeException("쿠폰 없음"));

            coupon.markAsUsed();
            couponRepository.save(coupon);
            return IssuedResultDto.success(coupon);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("쿠폰 없음");
        }

    }



}
