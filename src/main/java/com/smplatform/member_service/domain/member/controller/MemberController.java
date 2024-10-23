package com.smplatform.member_service.member.controller;

import com.smplatform.member_service.member.dto.MemberDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/members")
@Tag(name = "Member", description = "Member management APIs")
@RequiredArgsConstructor
public class MemberController {


    @GetMapping("/{memberId}")
    @Operation(summary = "member 조회", description = "해당 아이디의 사용자 정보 조회")
    public ResponseEntity<MemberDto> getMemberById(@PathVariable("memberId") Long memberId) {

    }

    @GetMapping
    @Operation(summary = "member 리스트 조회", description = "쿼리파라미터를 이용한 사용자 정보 리스트 조회")
    public ResponseEntity<List<MemberDto>> getMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ) {

    }

    @PostMapping
    @Operation(summary = "member 생성", description = "사용자 정보 생성")
    public ResponseEntity<Long> createMember(@RequestBody MemberDto memberDto) {

    }

    @PutMapping("/{memberId}")
    @Operation(summary = "member 정보 수정", description = "사용자 정보 수정")
    public ResponseEntity<MemberDto> updateMember(
            @PathVariable("memberId") Long memberId,
            @RequestBody MemberDto memberDto
    ) {

    }

    @DeleteMapping("/{memberId}")
    @Operation(summary = "member 삭제", description = "사용자 정보 삭제")
    public ResponseEntity<Void> deleteMember(@PathVariable("memberId") Long memberId) {

    }
}
