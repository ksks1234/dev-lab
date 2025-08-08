package com.example.coupon.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coupon_seq_gen")
    @SequenceGenerator(
            name = "coupon_seq_gen",
            sequenceName = "coupon_seq",  // 실제 Oracle에 생성할 시퀀스 이름
            allocationSize = 1
    )
    private Long id;
    private String code;
    private String name;
    @Enumerated(EnumType.STRING)
    private CouponStatus status;
    private LocalDateTime issuedAt;


    // ✅ 정적 팩토리 메서드
    public static Coupon create(String code, String name) {
        Coupon coupon = new Coupon();
        coupon.code = code;
        coupon.name = name;
        coupon.status = CouponStatus.UNUSED;
        coupon.issuedAt = null;
        return coupon;
    }

    // ✅ 상태 변경 도메인 메서드
    public void markAsUsed() {
        if (this.status != CouponStatus.UNUSED) {
            throw new IllegalStateException("이미 사용된 쿠폰입니다.");
        }
        this.status = CouponStatus.USED;
        this.issuedAt = LocalDateTime.now();
    }
}
