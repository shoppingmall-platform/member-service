package com.smplatform.member_service.domain.member.service;

import com.smplatform.member_service.domain.member.dto.MemberCreateDto;
import com.smplatform.member_service.domain.member.dto.MemberUpdateDto;
import com.smplatform.member_service.domain.member.dto.MemberResponseDto;
import com.smplatform.member_service.domain.member.dto.MemberSearchRequestParamDto;

import java.util.List;

public interface MemberService {

    /**
     * Create
     */
    Long createMember(MemberCreateDto newMemberDto);

    /**
     * Read
     */
    MemberResponseDto getMember(Long id);
    List<MemberResponseDto> getMembers();
    List<MemberResponseDto> searchMembers(MemberSearchRequestParamDto searchRequestParamDto);

    /**
     * Update
     */
    Long updateMember(Long id, MemberUpdateDto updateMemberDto);

    /**
     * Delete
     */
    Void deleteMember(Long id, String memo);
}
