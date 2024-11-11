package com.smplatform.member_service.domain.member.dto;

import com.smplatform.member_service.domain.member.enums.MemberAuthority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.smplatform.member_service.domain.member.enums.Gender;
import com.smplatform.member_service.domain.member.enums.MemberLevel;
import com.smplatform.member_service.domain.member.enums.MemberStatus;

@Builder
@Getter
@AllArgsConstructor
public class MemberResponseDto {
    private Long memberId;
    private String name;
    private String email;
    private LocalDate birthday;
    private String phoneNumber;
    private String gender;
    private String status;
    private String level;
    private String region;
    private LocalDateTime createAt;
    private String authority;
}
