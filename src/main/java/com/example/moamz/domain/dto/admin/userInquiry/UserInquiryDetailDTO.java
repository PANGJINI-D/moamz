package com.example.moamz.domain.dto.admin.userInquiry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserInquiryDetailDTO {
//  일반회원 문의글 상세보기  (미확인/답변완료 공통)
    private Long postId;                  // 게시글ID
    private Long userCode;                //  회원번호
    private String postTitle;             // 제목

    private String userFileId;            // 작성자 플필사진 id
    private String userFileRoot;          // 작성자 플필사진 파일경로
    private String userFileName;          // 작성자 플필사진 파일이름
    private String userFileUUID;          // 작성자 플필사진 파일UUID

    private String normalNickname;         // 작성자 닉네임(일반회원)
    private String postCreatedAt;           // 작성일
    private String inquiryStatus;          // 문의글 상태 (미확인/답변완료)
    private String inquiryContent;         // 글 내용
}
