package com.example.moamz.service.mypage.normal;

import com.example.moamz.domain.dto.mypage.normal.MyPostEcoDTO;
import com.example.moamz.domain.dto.mypage.normal.MyPostFreeDTO;
import com.example.moamz.domain.dto.mypage.normal.MyPostRecipeDTO;
import com.example.moamz.mapper.mypage.normal.NormalMyPostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NormalMyPostService {

    private final NormalMyPostMapper normalMyPostMapper;

    // 에코 프로젝트 게시글 조회
    public List<MyPostEcoDTO> getMyPostEco(Long userCode) {
        return normalMyPostMapper.selectMyPostEco();  // 에코 게시글 조회
    }

    // 레시피 게시글 조회
    public List<MyPostRecipeDTO> getMyPostRecipe(Long userCode) {
        return normalMyPostMapper.selectMyPostRecipe();  // 레시피 게시글 조회
    }

    // 자유 게시글 조회
    public List<MyPostFreeDTO> getMyPostFree(Long userCode) {
        return normalMyPostMapper.selectMyPostFree();  // 자유 게시글 조회
    }
}
