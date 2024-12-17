package com.example.moamz.domain.dto.shopping;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductShopDetailDTO {
    private Long fgProductId;
    private String fgProductName;
    private int fgProductPrice;
    private String fgProductWeight;
    private LocalDateTime fgProductExpDate;
    private int fgProductStock;
    private String fgProductContent;
    private LocalDateTime fgProductTime;
    private Long fgBusinessId;
    private String fgBusinessName;
    private String fgBusinessOpenTime;
    private String fgBusinessCloseTime;
    private String fgBusinessAddress;
    private String fgBusinessAddressDetail;
    private String fgBusinessPostCode;
    private String fgBusinessDetail;
    private String fgBusinessPhone;
    private String fgBusinessRating;
    private String fgBusinessLikes;

    private String productFileName;
    private String productFileRoot;
    private String productFileUuid;
}
