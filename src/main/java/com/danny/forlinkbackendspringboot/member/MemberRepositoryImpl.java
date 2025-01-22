package com.danny.forlinkbackendspringboot.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberCustomRepository{

    @Autowired
    private JPAQueryFactory queryFactory;

    public MemberRepositoryImpl() {
        super(Member.class);
    }

}
