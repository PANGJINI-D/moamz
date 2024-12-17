package com.example.moamz.service.shopping;

import com.example.moamz.domain.dto.shopping.KakaoPayReadyRequest;
import com.example.moamz.domain.dto.shopping.KakaoPayReadyResponse;
import com.example.moamz.domain.dto.shopping.OrderDTO;
import com.example.moamz.domain.dto.shopping.OrderDetailDTO;
import com.example.moamz.mapper.shopping.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KakaoPayService {

    @Value("${kakao.pay.key}")
    private String kakaoApiKey;

    @Value("${kakao.pay.cid}")
    private String kakaoPayCid;

    @Value("${kakao.pay.host}")
    private String kakaoPayHost;

    private final ProductMapper productMapper;


    String tid = UUID.randomUUID().toString();
    public KakaoPayReadyResponse ready(OrderDTO orderDTO) {

        // 사업자 ID 조회
        Long businessId = productMapper.getBusinessIdByProductId(orderDTO.getFgProductId());
        String productName = productMapper.getProductNameByProductId(orderDTO.getFgProductId());
        orderDTO.setFgBusinessId(businessId);

        // 주문 데이터 저장
        productMapper.insertOrder(orderDTO);



        // 주문 상세 데이터 저장
        OrderDetailDTO orderDetail = new OrderDetailDTO();
        orderDetail.setFgOrderDetailId(orderDTO.getFgOrderId());
        orderDetail.setFgTransactionId(tid);
        orderDetail.setFgOrderId(orderDTO.getFgOrderId());
        orderDetail.setFgProductName(productName);

        productMapper.insertOrderDetail(orderDetail);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "SECRET_KEY " + kakaoApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 카카오페이 요청 데이터 생성
        KakaoPayReadyRequest readyRequest = KakaoPayReadyRequest.builder()
                .cid(kakaoPayCid)
                .partner_order_id(orderDTO.getFgOrderId().toString())
                .partner_user_id(orderDTO.getFgUserCode().toString())
                .item_name(productName)
                .quantity(orderDTO.getFgOrderQuantity())
                .total_amount(orderDTO.getFgOrderAmount())
                .tax_free_amount(orderDTO.getFgOrderTax())
                .approval_url("http://localhost:9999/kakao/approve")
                .cancel_url("http://localhost:9999/kakao/cancel")
                .fail_url("http://localhost:9999/kakao/fail")
                .build();

        // 카카오페이 API 호출
        KakaoPayReadyResponse readyResponse = sendKakaoPayReadyRequest(readyRequest);
        this.tid = readyResponse.getTid(); // TID 저장
        return readyResponse;
    }

    /**
     * 카카오페이 결제 승인
     */
    public String approve(String pgToken) {
        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "SECRET_KEY " + kakaoApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 최근 생성된 주문 정보 가져오기
        OrderDTO orderDTO = productMapper.getLatestOrder(); // 최신 주문 정보를 조회하는 메서드 구현 필요
        if (orderDTO == null || tid == null) {
            throw new IllegalArgumentException("주문 정보 또는 TID가 유효하지 않습니다.");
        }

        // 승인 요청 데이터 생성
        Map<String, String> approveRequest = Map.of(
                "cid", kakaoPayCid,
                "tid", tid,
                "partner_order_id", orderDTO.getFgOrderId().toString(),
                "partner_user_id", orderDTO.getFgUserCode().toString(),
                "pg_token", pgToken
        );

        // 승인 요청 전송
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(approveRequest, headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                    kakaoPayHost + "/online/v1/payment/approve",
                    entity,
                    String.class
            );

            // 승인 성공 응답 처리
            return response.getBody();
        } catch (HttpStatusCodeException ex) {
            // 실패 응답 처리
            throw new RuntimeException("결제 승인 실패: " + ex.getResponseBodyAsString());
        }
    }



    /**
     * 카카오페이 결제 준비 요청
     */
    private KakaoPayReadyResponse sendKakaoPayReadyRequest(KakaoPayReadyRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "SECRET_KEY " + kakaoApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<KakaoPayReadyRequest> entity = new HttpEntity<>(request, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<KakaoPayReadyResponse> response = restTemplate.postForEntity(
                kakaoPayHost + "/online/v1/payment/ready",
                entity,
                KakaoPayReadyResponse.class
        );
        return response.getBody();
    }
}
