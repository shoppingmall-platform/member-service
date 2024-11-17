package com.smplatform.member_service.domain.member.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "withdraw_members")
public class WithdrawMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long withdrawMemberId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @NotBlank
    @Column(name = "id")
    private String id;

    @Column(name = "memo")
    private String memo;

    @CreationTimestamp
    @Column(name = "withdraw_at")
    private LocalDateTime withdrawAt;

    public WithdrawMember(Member member, String memo) {
        this.member = member;
        this.id = member.getId();
        this.memo = memo;
    }

}
