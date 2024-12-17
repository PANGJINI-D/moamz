package com.example.moamz.controller.shopping;

import com.example.moamz.domain.dto.shopping.KakaoPayReadyRequest;
import com.example.moamz.domain.dto.shopping.KakaoPayReadyResponse;
import com.example.moamz.domain.dto.shopping.OrderDTO;
import com.example.moamz.service.shopping.KakaoPayService;
import com.example.moamz.service.shopping.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@RestController
@RequestMapping("/kakao")
@RequiredArgsConstructor
@Controller
public class KakaoPayController {


    private final KakaoPayService kakaoPayService;
    private final ProductService productService;

    @PostMapping("/pay")
    public ResponseEntity<KakaoPayReadyResponse> readyPayment(
            @RequestBody OrderDTO orderDTO, HttpSession session) {
        // 세션에서 사용자 ID 가져오기
//        Long userCode = (Long) session.getAttribute("userCode");
//        if (userCode == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 사용자 인증 필요
//        }
        Long userCode = 1L;

        // 사용자 ID 설정
        orderDTO.setFgUserCode(userCode);

        // 카카오페이 결제 준비
        KakaoPayReadyResponse response = kakaoPayService.ready(orderDTO);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/approve")
    public String approvePayment(@RequestParam("pg_token") String pgToken, Model model) {

        String response = kakaoPayService.approve(pgToken);
        model.addAttribute("response", response);

        productService.approveOrder();

        return "shopping/kakaopayApprove";

    }

}
