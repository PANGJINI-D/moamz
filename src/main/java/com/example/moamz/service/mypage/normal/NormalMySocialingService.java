package com.example.moamz.service.mypage.normal;

import com.example.moamz.domain.dto.mypage.normal.MySocialingDTO;
import com.example.moamz.mapper.mypage.normal.MySocialingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
//@RequiredArgsConstructor
@Slf4j
public class NormalMySocialingService {

    private final MySocialingMapper mySocialingMapper;

    public NormalMySocialingService(MySocialingMapper mySocialingMapper) {
        this.mySocialingMapper = mySocialingMapper;
    }

    /**
     * 소셜링 데이터 목록 조회
     *
     * @return List<MySocialingDTO>
     */
    public List<MySocialingDTO> getMySocialings() {
        // MyBatis Mapper를 통해 데이터 조회
        return mySocialingMapper.selectMySocialing();
    }
}
