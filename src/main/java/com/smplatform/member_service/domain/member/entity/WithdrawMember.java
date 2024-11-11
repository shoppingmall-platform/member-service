package com.smplatform.member_service.domain.member.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name = "withdraw_members")
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawMember {
    @Id
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "email")
    private String email;

    @Column(name = "memo")
    private String memo;

    @Column(name = "withdraw_at")
    private LocalDateTime withdrawAt;

}
