package com.smplatform.member_service.domain.member.repository;

import com.smplatform.member_service.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, CustomMemberRepository {
    Optional<Member> findByMemberId(Long memberId);
    Optional<Member> findById(String id);
}