package com.example.moamz.mapper.mypage.normal;

import com.example.moamz.domain.dto.mypage.normal.MyLikeEcoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyLikeEcoMapper {
    List<MyLikeEcoDTO> selectMyLikeEco(Long fgUserCode);
}
