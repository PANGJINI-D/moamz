package com.example.moamz.controller.mypage.normal;

import com.example.moamz.domain.dto.mypage.normal.inquiry.NormalInquiryDetailDTO;
import com.example.moamz.domain.dto.mypage.normal.inquiry.NormalInquiryListDTO;
import com.example.moamz.domain.dto.mypage.normal.inquiry.NormalInquiryWriteDTO;
import com.example.moamz.domain.dto.page.Criteria;
import com.example.moamz.service.mypage.normal.NormalInquiryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("normal/inquiry")
public class NormalInquiryController {
    public final NormalInquiryService normalInquiryService;

    // 문의글 등록 페이지 열기
    @GetMapping("/regist")
    public String regist(@SessionAttribute(value = "fgUserCode", required = false) Long fgUserCode, Model model) {
        // 세션에 userCode가 null이면 로그인 페이지로 리다이렉트
        return fgUserCode == null ? "redirect:/normal/regular/userLogin" : "mypage/regular/userAdminInquiryWrite";

    }

    // 문의글 목록 페이지 열기
    @GetMapping("list")
    public String list(@SessionAttribute(value = "fgUserCode", required = false) Long fgUserCode,
                       Criteria criteria,
                       Model model) {

        // 세션에 fgUserCode가 null이면 로그인 페이지로 리다이렉트
        if (fgUserCode == null) {
            return "redirect:/normal/regular/userLogin";
        }


        // 사용자 코드 (fgUserCode) 를 이용해서 서비스 메서드 호출
        List<NormalInquiryListDTO> normalInquiryList = normalInquiryService.getNormalInquiryList(fgUserCode);
        model.addAttribute("normalInquiryList", normalInquiryList);

        // 템플릿 반환
        return "mypage/regular/userAdminInquiryList";

    }

    // 문의글 등록 post 요청 처리
    @PostMapping("/regist")
    public String regist(NormalInquiryWriteDTO normalInquiryWriteDTO,
                         @SessionAttribute(value = "fgUserCode", required = false) Long fgUserCode,
                         RedirectAttributes redirectAttributes) {
        // 세션의 사용자 코드 설정
        if(fgUserCode == null){
            return "redirect:/normal/regular/userLogin";
        }

        // 세션의 fgUserCode를 DTO에 넣기
        normalInquiryWriteDTO.setFgUserCode(fgUserCode);

        // 문의글 등록 메서드 호출
        normalInquiryService.normalRegistInquiry(normalInquiryWriteDTO);

        // 제대로 설정되었는지 확인
        if (normalInquiryWriteDTO.getFgPostId() != null) {
            System.out.println("👌fgPostId가 설정되었습니다 : " + normalInquiryWriteDTO.getFgPostId());
            return "redirect:/normal/inquiry/detail/" + normalInquiryWriteDTO.getFgPostId();
//            redirectAttributes.addFlashAttribute("fgPostId", normalInquiryWriteDTO.getFgPostId());
        } else {
            System.out.println("😒오류 : normalInquiryWriteDTO.getfgPostId()가 서비스 호출 후 null입니다");
            // 오류 발생시 -> 리다이렉트
            redirectAttributes.addFlashAttribute("errorMessage", "문의글 등록에 실패했습니다.");
            return "redirect:/normal/inquiry/list";
        }

    }

    // 문의글 상세보기 페이지 열기
    @GetMapping("detail/{fgPostId}")
    public String detail(@SessionAttribute(value = "fgUserCode", required = false) Long fgUserCode,
                         @PathVariable("fgPostId") Long fgPostId,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        // 세션에 fgUserCode가 null이면 로그인 페이지로 리다이렉트
        if (fgUserCode == null) {
            return "redirect:/normal/regular/userLogin";
        }

        // 문의글 상세 정보 불러오기
        NormalInquiryDetailDTO normalInquiryDetailDTO = normalInquiryService.findNormalInquiryDetail(fgPostId);

        // 데이터가 없는 경우 처리 (예 : 게시글이 삭제되었거나 존재하지 않음)
        if (normalInquiryDetailDTO == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "문의글을 찾을 수 없습니다.");
            return "redirect:/normal/inquiry/list";
        }

        // 문의글 작성자와 세션 사용자 일치 여부 확인
        if (!normalInquiryDetailDTO.getFgUserCode().equals(fgUserCode)) {
            redirectAttributes.addFlashAttribute("errorMessage", "본인만 문의글을 확인할 수 있습니다.");
            return "redirect:/normal/inquiry/list";
        }

        // 모델에 상세 정보 추가
        model.addAttribute("normalInquiryDetailDTO", normalInquiryDetailDTO);

        return "mypage/regular/userAdminInquiryDetail";
    }


    // 게시글 삭제하기
    @GetMapping("/remove/inquiry/{fgPostId}")
    public String normalDeletePost(@RequestParam("fgPostId") Long fgPostId,
                                   RedirectAttributes redirectAttributes) {
        // 게시글 삭제
        normalInquiryService.normalRemoveInquiry(fgPostId);

        redirectAttributes.addFlashAttribute("Message", "삭제되었습니다.");
        return "redirect:/normal/inquiry/list";
    }

}
