package com.example.coupon.service;

import com.example.coupon.domain.Coupon;
import com.example.coupon.domain.CouponStatus;
import com.example.coupon.dto.IssuedResultDto;
import com.example.coupon.exception.DuplicateIssueException;
import com.example.coupon.repository.CouponRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CouponService {
    private final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Transactional
    public IssuedResultDto issueCoupon(Long userId) {
        try {
            Coupon coupon = couponRepository.findFirstByStatus(CouponStatus.UNUSED)
                    .orElseThrow(() -> new RuntimeException("쿠폰 없음"));
            // 유저의 쿠폰 발급 여부 조회
            boolean isCouponIssued = couponRepository.existsById(userId);
            if (isCouponIssued) {
                // 쿠폰이 발급되어 있을 경우 409 응답
                throw new DuplicateIssueException("이미 발급된 사용자입니다.");
            } else {
                // 쿠폰이 발급되지 않았을 경우
                coupon.markAsUsed();
                couponRepository.save(coupon);
                return IssuedResultDto.success(coupon);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("쿠폰 없음");
        }
    }
}

