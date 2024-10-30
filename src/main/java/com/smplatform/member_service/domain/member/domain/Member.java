package com.smplatform.member_service.domain.member.domain;

import com.smplatform.member_service.domain.member.dto.MemberCreateDto;
import com.smplatform.member_service.domain.member.dto.MemberUpdateDto;
import com.smplatform.member_service.domain.member.enums.Gender;
import com.smplatform.member_service.domain.member.enums.MemberAuthority;
import com.smplatform.member_service.domain.member.enums.MemberLevel;
import com.smplatform.member_service.domain.member.enums.MemberStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Getter
@Builder
@Table(name = "members")
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private MemberAuthority authority;

    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private MemberLevel level;

    @Column(name = "region")
    private String region;

    @Column(name = "tos_agreement")
    private Boolean tosAgreement;

    @Column(name = "privacy_agreement")
    private Boolean privacyAgreement;

    @Column(name = "marketing_agreement")
    private Boolean marketingAgreement;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;


    public Member update(MemberUpdateDto memberUpdateDto) {
        Optional.ofNullable(memberUpdateDto.getName()).ifPresent(name -> this.name = name);
        Optional.ofNullable(memberUpdateDto.getBirthday()).ifPresent(birthday -> this.birthday = birthday);
        Optional.ofNullable(memberUpdateDto.getPhoneNumber()).ifPresent(phoneNumber -> this.phoneNumber = phoneNumber);
        Optional.ofNullable(memberUpdateDto.getGender()).ifPresent(gender -> this.gender = gender);
        Optional.ofNullable(memberUpdateDto.getStatus()).ifPresent(status -> this.status = status);
        Optional.ofNullable(memberUpdateDto.getLevel()).ifPresent(level -> this.level = level);
        Optional.ofNullable(memberUpdateDto.getRegion()).ifPresent(region -> this.region = region);
        Optional.ofNullable(memberUpdateDto.getTosAgreement()).ifPresent(tosAgreement -> this.tosAgreement = tosAgreement);
        Optional.ofNullable(memberUpdateDto.getPrivacyAgreement()).ifPresent(privacyAgreement -> this.privacyAgreement = privacyAgreement);
        Optional.ofNullable(memberUpdateDto.getMarketingAgreement()).ifPresent(marketingAgreement -> this.marketingAgreement = marketingAgreement);
        this.updateAt = LocalDateTime.now();

        return this;
    }

    public void delete() {
        this.status = MemberStatus.WITHDRAWN;
    }

}
