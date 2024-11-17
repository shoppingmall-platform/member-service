package com.smplatform.member_service.domain.member.controller;

import com.smplatform.member_service.domain.member.dto.MemberCreateDto;
import com.smplatform.member_service.domain.member.dto.MemberUpdateDto;
import com.smplatform.member_service.domain.member.dto.MemberResponseDto;
import com.smplatform.member_service.domain.member.dto.MemberSearchRequestParamDto;
import com.smplatform.member_service.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/members")
@Tag(name = "Member", description = "Member management APIs")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping
    @Operation(summary = "member 생성", description = "사용자 정보 생성")
    public ResponseEntity<Long> createMember(@RequestBody MemberCreateDto newMember) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(newMember));
    }

    @GetMapping("/{memberId}")
    @Operation(summary = "member 조회", description = "해당 아이디의 사용자 정보 조회")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getMember(memberId));
    }

    @GetMapping
    @Operation(summary = "member 리스트 조회", description = "모든 사용자 정보 조회")
    public ResponseEntity<List<MemberResponseDto>> getMembers() {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getMembers());
    }

    @PostMapping("/search")
    @Operation(summary = "member 검색", description = "검색파라미터를 이용한 사용자 정보 리스트 조회")
    public ResponseEntity<List<MemberResponseDto>> searchMembers(@RequestBody MemberSearchRequestParamDto searchParam) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.searchMembers(searchParam));
    }

    @PutMapping("/{memberId}")
    @Operation(summary = "member 정보 수정", description = "사용자 정보 수정")
    public ResponseEntity<Long> updateMember(
            @PathVariable("memberId") Long memberId,
            @RequestBody MemberUpdateDto updateMember
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.updateMember(memberId, updateMember));
    }

    @DeleteMapping("/{memberId}")
    @Operation(summary = "member 삭제", description = "사용자 정보 삭제")
    public ResponseEntity<Void> deleteMember(
            @PathVariable("memberId") Long memberId,
            @RequestBody String memo
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.deleteMember(memberId, memo));
    }
}
