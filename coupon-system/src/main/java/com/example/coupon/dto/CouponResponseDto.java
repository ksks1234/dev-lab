package com.example.coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
public class CouponResponseDto {
    private String code;
    private String status;
    private LocalDateTime issuedAt;

    // 생성자, getter, (필요시 builder)
}
