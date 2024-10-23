package com.smplatform.member_service.domain.member.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@Table(name = "members")
@AllArgsConstructor
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
    private String birthday;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "status")
    private String status;

    @Column(name = "authority")
    private String authority;

    @Column(name = "level")
    private String level;

    @Column(name = "region")
    private String region;

    @Column(name = "tos_agreement")
    private String tosAgreement;

    @Column(name = "privacy_agreement")
    private String privacyAgreement;

    @Column(name = "marketing_agreement")
    private String marketingAgreement;

    @Column(name = "create_at")
    private String createAt;

    @Column(name = "update_at")
    private String updateAt;

}
