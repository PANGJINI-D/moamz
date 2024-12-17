package com.example.moamz.domain.dto.shopping;

import lombok.Data;

@Data
public class KakaoPayReadyResponse {
    private String tid;
    private Boolean tms_result;
    private String created_at;
    private String next_redirect_pc_url;
}
