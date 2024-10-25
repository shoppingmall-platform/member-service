package com.smplatform.member_service.domain.member.service;

import com.querydsl.core.BooleanBuilder;
import com.smplatform.member_service.domain.member.domain.Member;
import com.smplatform.member_service.domain.member.domain.QMember;
import com.smplatform.member_service.domain.member.dto.MemberCreateDto;
import com.smplatform.member_service.domain.member.dto.MemberUpdateDto;
import com.smplatform.member_service.domain.member.dto.MemberResponseDto;
import com.smplatform.member_service.domain.member.dto.MemberSearchRequestParamDto;
import com.smplatform.member_service.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = false)
    public Long createMember(MemberCreateDto memberCreateDto) {
        // 중복 검사
        if (memberRepository.findByEmail(memberCreateDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("중복된 이메일입니다.");
        }

        // password 해시 처리

        // 저장할 멤버 엔티티 생성
        Member member = Member.builder()
                .email(memberCreateDto.getEmail())
                .name(memberCreateDto.getName())
                .password(memberCreateDto.getPassword())
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();

        return memberRepository.save(member).getMemberId();
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public List<MemberResponseDto> getMembers(MemberSearchRequestParamDto searchRequestParamDto) {
        // Query using JPAQueryFactory
        List<Member> members = memberRepository.searchMember(searchRequestParamDto);

        // Convert the result into a list of MemberResponseDto and return
        return members.stream()
                .map(m -> MemberResponseDto.builder()
                        .memberId(m.getMemberId())
                        .name(m.getName())
                        .email(m.getEmail())
                        .birthday(m.getBirthday())
                        .phoneNumber(m.getPhoneNumber())
                        .gender(m.getGender())
                        .status(m.getStatus())
                        .level(m.getLevel())
                        .region(m.getRegion())
                        .createAt(m.getCreateAt())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = false)
    public Long updateMember(Long id, MemberUpdateDto memberUpdateDto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        member.update(memberUpdateDto);

        return memberRepository.save(member).getMemberId();
    }

    @Override
    @Transactional(readOnly = false)
    public Void deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        member.delete();

        return null;
    }
}
