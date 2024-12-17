package com.example.moamz.domain.dto.shopping;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderDetailDTO {
    private Long fgOrderDetailId;
    private String fgTransactionId;
    private Long fgOrderId;
    private String fgProductName;
}
