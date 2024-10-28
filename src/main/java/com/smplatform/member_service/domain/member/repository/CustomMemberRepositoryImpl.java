package com.smplatform.member_service.domain.member.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smplatform.member_service.domain.member.domain.Member;
import com.smplatform.member_service.domain.member.dto.MemberSearchRequestParamDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.smplatform.member_service.domain.member.domain.QMember.member;

@RequiredArgsConstructor
public class CustomMemberRepositoryImpl implements CustomMemberRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Member> searchMember(MemberSearchRequestParamDto searchRequestParamDto) {
        BooleanBuilder whereClause = new BooleanBuilder();

        if (searchRequestParamDto.getName() != null) {
            whereClause.and(member.name.eq(searchRequestParamDto.getName()));
        }
        if (searchRequestParamDto.getEmail() != null) {
            whereClause.and(member.email.eq(searchRequestParamDto.getEmail()));
        }
        if (searchRequestParamDto.getBirthday() != null) {
            whereClause.and(member.birthday.eq(searchRequestParamDto.getBirthday()));
        }
        if (searchRequestParamDto.getPhoneNumber() != null) {
            whereClause.and(member.phoneNumber.eq(searchRequestParamDto.getPhoneNumber()));
        }
        if (searchRequestParamDto.getGender() != null) {
            whereClause.and(member.gender.eq(searchRequestParamDto.getGender()));
        }
        if (searchRequestParamDto.getStatus() != null) {
            whereClause.and(member.status.eq(searchRequestParamDto.getStatus()));
        }
        if (searchRequestParamDto.getLevel() != null) {
            whereClause.and(member.level.eq(searchRequestParamDto.getLevel()));
        }
        if (searchRequestParamDto.getRegion() != null) {
            whereClause.and(member.region.eq(searchRequestParamDto.getRegion()));
        }
        if (searchRequestParamDto.getCreateAt() != null) {
            whereClause.and(member.createAt.eq(searchRequestParamDto.getCreateAt()));
        }

        return queryFactory
                .selectFrom(member)
                .where(whereClause)
                .fetch();
    }
}
