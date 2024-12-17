package com.example.moamz.controller.mypage.normal;

import com.example.moamz.domain.dto.mypage.normal.MySocialingDTO;
import com.example.moamz.service.mypage.normal.NormalMySocialingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
//@RequiredArgsConstructor
public class NormalMySocialingController {

    private final NormalMySocialingService normalMySocialingService;

    public NormalMySocialingController(NormalMySocialingService normalmySocialingService) {
        this.normalMySocialingService = normalmySocialingService;
    }

    @GetMapping("/normal/my/socialing")
    public String getMySocialings(Model model) {
        // Service를 통해 소셜링 데이터를 조회
        List<MySocialingDTO> socialingList = normalMySocialingService.getMySocialings();

        // 조회된 데이터 Model에 추가
        model.addAttribute("socialings", socialingList);

        // 연결될 Thymeleaf 템플릿 파일 경로 반환
        return "mypage/userMySocialing";
    }
}
