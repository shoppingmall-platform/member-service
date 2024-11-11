package com.smplatform.member_service.domain.member.service;

import com.smplatform.member_service.domain.member.entity.Member;
import com.smplatform.member_service.domain.member.dto.MemberCreateDto;
import com.smplatform.member_service.domain.member.dto.MemberUpdateDto;
import com.smplatform.member_service.domain.member.entity.WithdrawMember;
import com.smplatform.member_service.domain.member.enums.Gender;
import com.smplatform.member_service.domain.member.enums.MemberAuthority;
import com.smplatform.member_service.domain.member.enums.MemberLevel;
import com.smplatform.member_service.domain.member.enums.MemberStatus;
import com.smplatform.member_service.domain.member.dto.MemberResponseDto;
import com.smplatform.member_service.domain.member.dto.MemberSearchRequestParamDto;
import com.smplatform.member_service.domain.member.repository.MemberRepository;
import com.smplatform.member_service.domain.member.repository.WithdrawMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final WithdrawMemberRepository withdrawMemberRepository;

    @Override
    @Transactional(readOnly = false)
    public Long createMember(MemberCreateDto memberCreateDto) {
        // 중복 검사
        if (memberRepository.findByEmail(memberCreateDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("중복된 이메일입니다.");
        }

        // password 해시 처리
        String hashedPassword = passwordEncoder.encode(memberCreateDto.getPassword());

        // 저장할 멤버 엔티티 생성
        Member newMember = Member.builder()
                .name(memberCreateDto.getName())
                .email(memberCreateDto.getEmail())
                .password(hashedPassword)
                .birthday(memberCreateDto.getBirthday())
                .phoneNumber(memberCreateDto.getPhoneNumber())
                .gender(Gender.valueOf(memberCreateDto.getGender()))
                .region(memberCreateDto.getRegion())
                .tosAgreement(memberCreateDto.getTosAgreement())
                .privacyAgreement(memberCreateDto.getPrivacyAgreement())
                .marketingAgreement(memberCreateDto.getMarketingAgreement())
                .status(MemberStatus.ACTIVE)
                .authority(MemberAuthority.USER)
                .level(MemberLevel.NEW)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();

        return memberRepository.save(newMember).getMemberId();
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
                .gender(String.valueOf(member.getGender()))
                .status(String.valueOf(member.getStatus()))
                .level(String.valueOf(member.getLevel()))
                .region(member.getRegion())
                .createAt(member.getCreateAt())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberResponseDto> getMembers() {
        List<Member> members = memberRepository.findAll();

        return getMemberResponseDtos(members);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberResponseDto> searchMembers(MemberSearchRequestParamDto searchRequestParamDto) {
        // OrderSearch, OrderDateSearch, ProductId가 null이 아니면 필요 데이터 Rest API 요청
        

        List<Member> members = memberRepository.searchMember(searchRequestParamDto);

        return getMemberResponseDtos(members);
    }

    private List<MemberResponseDto> getMemberResponseDtos(List<Member> members) {
        return members.stream()
                .map(m -> MemberResponseDto.builder()
                        .memberId(m.getMemberId())
                        .name(m.getName())
                        .email(m.getEmail())
                        .birthday(m.getBirthday())
                        .phoneNumber(m.getPhoneNumber())
                        .gender(String.valueOf(m.getGender()))
                        .status(String.valueOf(m.getStatus()))
                        .level(String.valueOf(m.getLevel()))
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
    public Void deleteMember(Long id, String memo) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        member.delete();
        memberRepository.save(member);

        WithdrawMember withdrawMember = WithdrawMember.builder()
                .member(member)
                .email(member.getEmail())
                .memo(memo)
                .withdrawAt(LocalDateTime.now())
                .build();

        withdrawMemberRepository.save(withdrawMember);

        return null;
    }
}
