package com.example.moamz.domain.dto.shopping;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderDTO {
    private Long fgOrderId;
    private String fgPaymentStatus;
    private int fgOrderQuantity;
    private int fgOrderPerPrice;
    private int fgOrderTax;
    private int fgOrderUsedPoint;
    private int fgOrderAmount;
    private String fgOrderDate;
    private String fgProductName;
    private Long fgUserCode;
    private Long fgProductId;
    private Long fgBusinessId;
}
