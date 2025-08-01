package com.example.coupon.controller;

import com.example.coupon.domain.Coupon;
import com.example.coupon.dto.IssuedResultDto;
import com.example.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CouponController {
    private final CouponService couponService;

    @GetMapping("/get-coupon")
    public ResponseEntity<IssuedResultDto> getCoupon() {
        IssuedResultDto result = couponService.issueCoupon();
        return ResponseEntity.ok(result);
    }

}
