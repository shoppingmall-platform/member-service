package com.smplatform.member_service.domain.member.service;

import com.smplatform.member_service.domain.member.dto.MemberCreationDto;
import com.smplatform.member_service.domain.member.dto.MemberRequestDto;
import com.smplatform.member_service.domain.member.dto.MemberResponseDto;
import com.smplatform.member_service.domain.member.dto.MemberSearchRequestParamDto;

import java.util.List;

public interface MemberService {

    /**
     * Create
     */
    Long createMember(MemberCreationDto newMember);

    /**
     * Read
     */
    MemberResponseDto getMember(Long id);
    List<MemberResponseDto> getMembers(MemberSearchRequestParamDto searchParam);

    /**
     * Update
     */
    Long updateMember(MemberRequestDto updatedMember);

    /**
     * Delete
     */
    Void deleteMember(Long id);
}
