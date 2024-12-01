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
    String createMember(MemberCreateDto newMemberDto);

    /**
     * Read
     */
    MemberResponseDto getMember(String id);
    List<MemberResponseDto> getMembers();
    List<MemberResponseDto> searchMembers(MemberSearchRequestParamDto searchRequestParamDto);

    /**
     * Update
     */
    String updateMember(String id, MemberUpdateDto updateMemberDto);

    /**
     * Delete
     */
    Void deleteMember(String id, String memo);
}
