package com.example.moamz.service.mypage.normal;

import com.example.moamz.domain.dto.mypage.normal.MyReviewDTO;
import com.example.moamz.mapper.mypage.normal.MyReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class NormalMyReviewService {

    private final MyReviewMapper myReviewMapper;


    public List<MyReviewDTO> getMyReviews() {
        log.info("리뷰 확인");
        return myReviewMapper.selectReviewList();
    }
}
