package com.example.moamz.service.mypage.normal;


import com.example.moamz.domain.dto.mypage.normal.MyLikeEcoDTO;
import com.example.moamz.domain.dto.mypage.normal.MyLikeFreeDTO;
import com.example.moamz.domain.dto.mypage.normal.MyLikeRecipeDTO;
import com.example.moamz.mapper.mypage.normal.MyLikeEcoMapper;
import com.example.moamz.mapper.mypage.normal.MyLikeFreeMapper;
import com.example.moamz.mapper.mypage.normal.MyLikeRecipeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NormalMyLikeService {

    private final MyLikeEcoMapper myLikeEcoMapper;
    private final MyLikeFreeMapper myLikeFreeMapper;
    private final MyLikeRecipeMapper myLikeRecipeMapper;

    public List<MyLikeEcoDTO> getMyLikeEco(Long fgUserCode) {
        return myLikeEcoMapper.selectMyLikeEco(fgUserCode);
    }

    public List<MyLikeRecipeDTO> getMyLikeRecipe(Long fgUserCode) {
        return myLikeRecipeMapper.selectMyLikeRecipe(fgUserCode);
    }

    public List<MyLikeFreeDTO> getMyLikeFree(Long fgUserCode) {
        return myLikeFreeMapper.selectMyLikeFree(fgUserCode);
    }
}
