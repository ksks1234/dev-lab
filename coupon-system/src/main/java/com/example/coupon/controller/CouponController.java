package com.example.coupon.controller;

import com.example.coupon.domain.Coupon;
import com.example.coupon.dto.IssuedResultDto;
import com.example.coupon.exception.DuplicateIssueException;
import com.example.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CouponController {
    private final CouponService couponService;

    @GetMapping("/get-coupon")
    public ResponseEntity<IssuedResultDto> getCoupon(@RequestParam Long userId) {
        try {
            IssuedResultDto result = couponService.issueCoupon(userId);
            return ResponseEntity.ok(result); // 200 ok
        }catch (DuplicateIssueException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(IssuedResultDto.fail("이미 쿠폰을 발급받은 사용자입니다."));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(IssuedResultDto.fail("서버 오류"));
        }
    }

}
