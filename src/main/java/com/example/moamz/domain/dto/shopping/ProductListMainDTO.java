package com.example.moamz.domain.dto.shopping;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductListMainDTO {
    private Long fgProductId;
    private String fgProductName;
    private int fgProductPrice;
    private String fgProductWeight;
    private LocalDateTime fgProductExpDate;
    private int fgProductStock;
    private String fgProductContent;
    private LocalDateTime fgProductTime;
    private String fgSubCategoryId;
    private String fgSubCategoryName;
    private Long fgBusinessId;
    private String fgBusinessName;

    private String productFileName;
    private String productFileRoot;
    private String productFileUuid;
}
