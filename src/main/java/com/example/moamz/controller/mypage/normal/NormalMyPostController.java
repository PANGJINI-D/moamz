package com.example.moamz.controller.mypage.normal;

import com.example.moamz.domain.dto.mypage.normal.MyPostEcoDTO;
import com.example.moamz.domain.dto.mypage.normal.MyPostFreeDTO;
import com.example.moamz.domain.dto.mypage.normal.MyPostRecipeDTO;
import com.example.moamz.mapper.mypage.normal.NormalMyPostMapper;
import com.example.moamz.service.mypage.normal.NormalMyPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NormalMyPostController {

    private final NormalMyPostService normalMyPostService;
    private final NormalMyPostMapper normalMyPostMapper;

    // 마이페이지 > 에코 프로젝트 게시글 목록
    @GetMapping("/normal/my/postEco")
    public String getMyPostEco(@SessionAttribute(value = "fgUserCode", required = false) Long userCode, Model model) {
        // 세션에 userCode가 없으면 로그인 페이지로 리다이렉트
        if (userCode == null) {
            return "redirect:/normal/regular/userLogin";
        }

        // 사용자 코드 (userCode)를 이용하여 에코 프로젝트 게시글 조회
        List<MyPostEcoDTO> myPostEcoList = normalMyPostService.getMyPostEco(userCode);
        model.addAttribute("myPostEcoList", myPostEcoList);

        return "mypage/regular/userMyPost";  // 뷰 템플릿
    }

    // 마이페이지 > 레시피 게시글 목록
    @GetMapping("/normal/my/postRecipe")
    public String getMyPostRecipe(@SessionAttribute(value = "fgUserCode", required = false) Long userCode, Model model) {
        // 세션에 userCode가 없으면 로그인 페이지로 리다이렉트
        if (userCode == null) {
            return "redirect:/normal/regular/userLogin";
        }

        // 사용자 코드 (userCode)를 이용하여 레시피 게시글 조회
        List<MyPostRecipeDTO> myPostRecipeList = normalMyPostService.getMyPostRecipe(userCode);
        model.addAttribute("myPostRecipeList", myPostRecipeList);

        return "mypage/regular/myPostRecipe";  // 뷰 템플릿
    }

    // 마이페이지 > 자유 게시글 목록
    @GetMapping("/normal/my/postFree")
    public String getMyPostFree(@SessionAttribute(value = "fgUserCode", required = false) Long userCode, Model model) {
        // 세션에 userCode가 없으면 로그인 페이지로 리다이렉트
        if (userCode == null) {
            return "redirect:/normal/regular/userLogin";
        }

        // 사용자 코드 (userCode)를 이용하여 자유 게시글 조회
        List<MyPostFreeDTO> myPostFreeList = normalMyPostService.getMyPostFree(userCode);
        model.addAttribute("myPostFreeList", myPostFreeList);

        return "mypage/regular/myPostFree";  // 뷰 템플릿
    }
}
