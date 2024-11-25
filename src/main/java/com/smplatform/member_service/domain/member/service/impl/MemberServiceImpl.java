package com.smplatform.member_service.domain.member.service.impl;

import com.smplatform.member_service.domain.member.entity.Member;
import com.smplatform.member_service.domain.member.dto.MemberCreateDto;
import com.smplatform.member_service.domain.member.dto.MemberUpdateDto;
import com.smplatform.member_service.domain.member.entity.WithdrawMember;
import com.smplatform.member_service.domain.member.dto.MemberResponseDto;
import com.smplatform.member_service.domain.member.dto.MemberSearchRequestParamDto;
import com.smplatform.member_service.domain.member.exception.IdDuplicateException;
import com.smplatform.member_service.domain.member.exception.MemberNotFoundException;
import com.smplatform.member_service.domain.member.repository.MemberRepository;
import com.smplatform.member_service.domain.member.repository.WithdrawMemberRepository;
import com.smplatform.member_service.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final WithdrawMemberRepository withdrawMemberRepository;

    @Override
    @Transactional(readOnly = false)
    public Long createMember(MemberCreateDto memberCreateDto) {
        // 아이디 중복 검사
        if (memberRepository.findById(memberCreateDto.getId()).isPresent()) {
            throw new IdDuplicateException(memberCreateDto.getId());
        }

        // password 해시 처리
        String hashedPassword = passwordEncoder.encode(memberCreateDto.getPassword());

        // 저장할 멤버 엔티티 생성
        Member newMember = memberCreateDto.toEntity(hashedPassword);

        return memberRepository.save(newMember).getMemberId();
    }

    @Override
    @Transactional(readOnly = true)
    public MemberResponseDto getMember(Long memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        return new MemberResponseDto(member);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberResponseDto> getMembers() {
        List<Member> members = memberRepository.findAll();

        return members.stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberResponseDto> searchMembers(MemberSearchRequestParamDto searchRequestParamDto) {
        // OrderSearch, OrderDateSearch, ProductId가 null이 아니면 필요 데이터 Rest API 요청
        

        List<Member> members = memberRepository.searchMember(searchRequestParamDto);

        return members.stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = false)
    public Long updateMember(Long memberId, MemberUpdateDto memberUpdateDto) {

        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        member.update(memberUpdateDto);

        return memberRepository.save(member).getMemberId();
    }

    @Override
    @Transactional(readOnly = false)
    public Void deleteMember(Long memberId, String memo) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        member.delete();
        memberRepository.save(member);

        WithdrawMember withdrawMember = new WithdrawMember(member, memo);
        withdrawMemberRepository.save(withdrawMember);

        return null;
    }
}
