package com.example.moamz.mapper.mypage.normal;

import com.example.moamz.domain.dto.mypage.normal.MyLikeFreeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyLikeFreeMapper {
    List<MyLikeFreeDTO> selectMyLikeFree(Long fgUserCode);
}
