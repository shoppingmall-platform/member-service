package com.smplatform.member_service.domain.member.service;

import com.smplatform.member_service.domain.member.domain.Member;
import com.smplatform.member_service.domain.member.dto.MemberCreationDto;
import com.smplatform.member_service.domain.member.dto.MemberRequestDto;
import com.smplatform.member_service.domain.member.dto.MemberResponseDto;
import com.smplatform.member_service.domain.member.dto.MemberSearchRequestParamDto;
import com.smplatform.member_service.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Long createMember(MemberCreationDto newMember) {
        // 중복 검사
        if (memberRepository.findByEmail(newMember.getEmail())) {
            throw new IllegalArgumentException("중복된 이메일입니다.");
        }

        // password 해시 처리

        // 저장할 멤버 엔티티 생성
        Member member = Member.builder()
                .email(newMember.getEmail())
                .name(newMember.getName())
                .password(newMember.getPassword())
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();


        // 저장
        Member savedMember = memberRepository.save(member);

        return savedMember.getMemberId();
    }

    @Override
    public MemberResponseDto getMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        return MemberResponseDto.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .email(member.getEmail())
                .birthday(member.getBirthday())
                .phoneNumber(member.getPhoneNumber())
                .gender(member.getGender())
                .status(member.getStatus())
                .level(member.getLevel())
                .region(member.getRegion())
                .createAt(member.getCreateAt())
                .build();
    }

    @Override
    public List<MemberResponseDto> getMembers(MemberSearchRequestParamDto searchParam) {
        return List.of();
    }

    @Override
    public Long updateMember(MemberRequestDto updatedMember) {
        return 0;
    }

    @Override
    public Void deleteMember(Long id) {
        return null;
    }
}
