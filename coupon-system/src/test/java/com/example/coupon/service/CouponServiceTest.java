package com.example.coupon.service;

import com.example.coupon.domain.Coupon;
import com.example.coupon.domain.User;
import com.example.coupon.dto.IssuedResultDto;
import com.example.coupon.exception.DuplicateIssueException;
import com.example.coupon.repository.CouponHistoryRepository;
import com.example.coupon.repository.CouponRepository;
import com.example.coupon.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CouponServiceTest {

    @Autowired
    private CouponService couponService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CouponHistoryRepository couponHistoryRepository;

    private Long userId1;
    private Long userId2;

    @BeforeEach
    void setUp() {
        // 유저 등록
        User user1 = userRepository.save(new User("철수", "chul@example.com"));
        User user2 = userRepository.save(new User("영희", "young@example.com"));
        userId1 = user1.getUserId();
        userId2 = user2.getUserId();

        // 쿠폰 등록
        couponRepository.save(Coupon.create("COUPON1", "쿠폰1"));
        couponRepository.save(Coupon.create("COUPON3", "쿠폰3"));
    }

    @Test
    void 첫_발급은_성공해야_한다() {
        IssuedResultDto result = couponService.issueCoupon(userId1);

        assertTrue(result.isSuccess());
        assertNotNull(result.getData());
        System.out.println("발급된 쿠폰 코드: " + result.getData().getCode());
    }

    @Test
    void 두번째_요청은_중복_예외가_발생해야_한다() {
        couponService.issueCoupon(userId1);

        assertThrows(DuplicateIssueException.class, () -> {
            couponService.issueCoupon(userId1);
        });
    }

    @Test
    void 다른_유저는_정상적으로_발급_받는다() {
        couponService.issueCoupon(userId1);
        IssuedResultDto result = couponService.issueCoupon(userId2);

        assertTrue(result.isSuccess());
    }

    @Test
    void 쿠폰_재고가_없으면_예외가_발생한다() {
        couponService.issueCoupon(userId1);
        couponService.issueCoupon(userId2);

        // 새로운 유저 추가
        User user3 = userRepository.save(new User("민수", "min@example.com"));
        Long userId3 = user3.getUserId();

        assertThrows(RuntimeException.class, () -> {
            couponService.issueCoupon(userId3);
        });
    }
}
