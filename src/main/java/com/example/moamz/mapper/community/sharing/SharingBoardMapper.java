package com.example.moamz.mapper.community.sharing;

import com.example.moamz.domain.dto.page.Criteria;
import com.example.moamz.domain.dto.community.sharing.SharingDetailDTO;
import com.example.moamz.domain.dto.community.sharing.SharingListDTO;
import com.example.moamz.domain.dto.community.sharing.SharingModifyDTO;
import com.example.moamz.domain.dto.community.sharing.SharingWriteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SharingBoardMapper {

    // 게시글 작성
    void insertPost(SharingWriteDTO sharingWriteDTO);
    void insertSharing(SharingWriteDTO sharingWriteDTO);

    // 게시글 수정
    void modifyPost(SharingModifyDTO sharingModifyDTO);
    void modifySharing(SharingModifyDTO sharingModifyDTO);

    // 게시글 삭제
    void deleteSharing(Long postId);

    // 게시글 목록 가져오기
    List<SharingListDTO> selectAvailableList();
    List<SharingListDTO> selectReservedList();
    List<SharingListDTO> selectCompletedList();

    // 게시글 목록 가져오기 (페이지네이션O)
    List<SharingListDTO> selectAvailableListAll(Criteria criteria);
    List<SharingListDTO> selectReservedListAll(Criteria criteria);
    List<SharingListDTO> selectCompletedListAll(Criteria criteria);

    // 자유게시판 전체 게시글 수 반환
    int selectAvailableTotal();
    int selectReservedTotal();
    int selectCompletedTotal();

    // 게시글 상세보기
    Optional<SharingDetailDTO> selectSharingDetail(Long postId);
    Optional<SharingDetailDTO> selectSharingDetail(Long postId, Long userCode);

}
