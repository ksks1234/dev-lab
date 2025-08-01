package com.example.coupon.dto;

import com.example.coupon.domain.Coupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class IssuedResultDto {

    private boolean success;         // 성공 여부
    private String message;          // 사용자에게 보여줄 메시지
    private CouponResponseDto data;  // 발급된 쿠폰 정보 (nullable)

    // 생성자, getter 생략 가능 (Lombok 쓰면 @Builder도 가능)

    public static IssuedResultDto success(Coupon coupon) {
        return new IssuedResultDto(
                true,
                "쿠폰 발급 성공",
                CouponResponseDto.builder()
                        .code(coupon.getCode())
                        .status(coupon.getStatus().name())
                        .issuedAt(coupon.getIssuedAt())
                        .build()
        );
    }

    public static IssuedResultDto fail(String message) {
        return new IssuedResultDto(false, message, null);
    }
}

