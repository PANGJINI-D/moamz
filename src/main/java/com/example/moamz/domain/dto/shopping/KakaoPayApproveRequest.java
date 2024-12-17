package com.example.moamz.domain.dto.shopping;

import lombok.Data;

@Data
public class KakaoPayApproveRequest {
    private String cid;
    private String tid;
    private String partnerOrderId;
    private String partnerUserId;
    private String pgToken;
}
