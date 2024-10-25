package com.smplatform.member_service.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
public class MemberUpdateDto {
    private String name;
    private LocalDate birthday;
    private String phoneNumber;
    private String gender;
    private String status;
    private String level;
    private String region;
    private Boolean tosAgreement;
    private Boolean privacyAgreement;
    private Boolean marketingAgreement;
}
