package com.example.moamz.controller.mypage.normal;

import com.example.moamz.domain.dto.mypage.normal.MyLikeEcoDTO;
import com.example.moamz.domain.dto.mypage.normal.MyLikeFreeDTO;
import com.example.moamz.domain.dto.mypage.normal.MyLikeRecipeDTO;
import com.example.moamz.service.mypage.normal.NormalMyLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/normal/my/like")
@RequiredArgsConstructor
@Slf4j
public class NormalMyLikeController {

    private final NormalMyLikeService normalMyLikeService;

    @GetMapping("/eco")
    public List<MyLikeEcoDTO> getMyLikeEco(@RequestParam Long fgUserCode) {
        log.info("에코 게시글 좋아요 : {}", fgUserCode);
        return normalMyLikeService.getMyLikeEco(fgUserCode);
    }

    @GetMapping("/recipe")
    public List<MyLikeRecipeDTO> getMyLikeRecipe(@RequestParam Long fgUserCode) {
        log.info("레시피 게시글 좋아요 : {}", fgUserCode);
        return normalMyLikeService.getMyLikeRecipe(fgUserCode);
    }

    @GetMapping("/free")
    public List<MyLikeFreeDTO> getMyLikeFree(@RequestParam Long fgUserCode) {
        log.info("자유게시판 게시글 좋아요 : {}", fgUserCode);
        return normalMyLikeService.getMyLikeFree(fgUserCode);
    }
}

