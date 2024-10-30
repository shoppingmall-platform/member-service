package com.smplatform.member_service.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

import com.smplatform.member_service.domain.member.enums.Gender;

@Builder
@Getter
@AllArgsConstructor
public class MemberCreateDto {
    private String name;
    private String email;
    private String password;
    private LocalDate birthday;
    private String phoneNumber;
    private Gender gender;
    private String region;
    private Boolean tosAgreement;
    private Boolean privacyAgreement;
    private Boolean marketingAgreement;
}
