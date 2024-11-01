package com.smplatform.member_service.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
