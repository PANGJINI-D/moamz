package com.example.moamz.domain.dto.shopping;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
public class KakaoPayReadyRequest {
    private String cid;  // 가맹점 코드 (테스트: "TC0ONETIME")
    private String partner_order_id;  // 주문 ID
    private String partner_user_id;   // 사용자 ID
    private String item_name;         // 상품 이름
    private int quantity;             // 수량
    private int total_amount;         // 총 금액
    private int tax_free_amount;      // 비과세 금액
    private String approval_url;      // 결제 성공 시 URL
    private String cancel_url;        // 결제 취소 시 URL
    private String fail_url;          // 결제 실패 시 URL

}
