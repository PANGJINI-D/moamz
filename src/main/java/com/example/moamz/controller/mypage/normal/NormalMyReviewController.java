package com.example.moamz.controller.mypage.normal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.moamz.domain.dto.mypage.normal.MyReviewDTO;
import com.example.moamz.mapper.mypage.normal.MyReviewMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequestMapping("/normal/my/review")
@RequiredArgsConstructor
@Slf4j
public class NormalMyReviewController {

    private final MyReviewMapper myReviewMapper;

    @GetMapping
    public String viewMyReviews(Model model) {
        log.info("마이페이지 리뷰");

        // 사용자 리뷰 목록 조회
        List<MyReviewDTO> reviews = myReviewMapper.selectReviewList();

        // 모델에 데이터 추가
        model.addAttribute("normalMyReview", reviews);

        // 사용자 페이지를 리턴
        return "mypage/normal/userMyReview";
    }
}