package com.smplatform.member_service.domain.member.repository;

import com.smplatform.member_service.domain.member.entity.Member;
import com.smplatform.member_service.domain.member.dto.MemberSearchRequestParamDto;

import java.util.List;

public interface CustomMemberRepository {
    List<Member> searchMember(final MemberSearchRequestParamDto searchRequestParamDto);
}
