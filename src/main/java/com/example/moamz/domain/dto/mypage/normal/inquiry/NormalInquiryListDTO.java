package com.example.moamz.domain.dto.mypage.normal.inquiry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NormalInquiryListDTO {
    private Long fgPostId;
    private String fgPostTitle;
    private String fgPostCreatedAt;
    private String fgInquiryStatus;
    private Long fgUserCode;
}
