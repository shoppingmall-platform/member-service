package com.smplatform.member_service.domain.member.service;

import com.smplatform.member_service.domain.member.dto.MemberDto;

import java.util.List;

public interface MemberService {

    /**
     * Create
     */
    Long createMember(MemberDto newMember);

    /**
     * Read
     */
    MemberDto getMember(Long id);
    List<MemberDto> getMembers();>

    /**
     * Update
     */
    MemberDto updateMember(MemberDto updatedMember);

    /**
     * Delete
     */
    Void deleteMember(Long id);
}
