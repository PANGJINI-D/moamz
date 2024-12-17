package com.example.moamz.mapper.mypage.normal;

import com.example.moamz.domain.dto.mypage.normal.MyLikeRecipeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyLikeRecipeMapper {
    List<MyLikeRecipeDTO> selectMyLikeRecipe(Long fgUserCode);
}
