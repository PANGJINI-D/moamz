package com.example.moamz.domain.dto.main;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MainPageDTO {
    private Long fgUserCode;

    // 업체 상세 페이지
    private Long fgPostId;
    // 업체 사진 파일
    private Long fgUserFileId;            // 파일ID
    private String fgUserFileName;        // 파일명
    private String fgUserFileRoot;        // 파일경로
    private String fgUserFileUuid;        // 파일UUID


    // 에코, 레시피, 소셜링 이미지 파일
    private Long fgPostFileId;            // 파일ID
    private String fgPostFileName;        // 파일명
    private String fgPostFileRoot;        // 파일경로
    private String fgPostFileUuid;        // 파일UUID
    private String thumbnailPath;       // 썸네일 경로

    private String fgBusinessName;
    private String fgBusinessOpenTime;
    private String fgBusinessCloseTime;
    private String fgBusinessAddress;
    private String fgBusinessAddressDetail;


    // 에코프로젝트 상세 페이지
    // 에코프로젝트 사진 파일
    private String fgPostTitle;
    private String fgEcoStart;
    private LocalDateTime fgEcoEnd;
    private int fgPostLikes;
    private String fgCertInfo;


    // 레시피 목록 페이지
    // 레시피 사진 파일
    // 레시피 글 제목
    private Long fgRecipeTime;
    // 레시피 종아요 수


    // 소셜링 목록 페이지
    // 소셜링 사진 파일
    // 소셜링 글 제목
    private LocalDateTime fgSocialingStart;
    private LocalDateTime fgSocialingEnd;
    private Long fgSocialingMaxParticipants;
    private Long fgSocialingMaxAge;
    private Long fgSocialingMinAge;
    private String fgSocialingGenderRes;

}
