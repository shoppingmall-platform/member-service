package com.smplatform.member_service.domain.member.exception;

import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends AbstractApiException {
    public MemberNotFoundException(Long id) {
        super("해당 회원 ID를 찾을 수 없습니다. ID: " + id);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}