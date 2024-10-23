package com.smplatform.member_service.member.repository;

import com.smplatform.member_service.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
