package com.example.moamz.domain.dto.mypage.normal.inquiry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminInquiryDetailDTO {
    private Long fgUserCode;
    private String fgPostTitle;
    private String fgNormalNickname;
    private String fgInquiryContent;
    private String fgPostCreatedAt;

}
