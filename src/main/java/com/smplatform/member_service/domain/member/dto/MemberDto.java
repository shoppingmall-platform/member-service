package com.smplatform.member_service.member.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class MemberDto {
    private Long memberId;

    private String name;

    private String email;
    
    // password 제외

    private String birthday;

    private String phoneNumber;

    private String gender;

    private String status;

    private String authority;

    private String level;

    private String region;

    private String tosAgreement;

    private String privacyAgreement;

    private String marketingAgreement;

    private String createAt;

    private String updateAt;

}
