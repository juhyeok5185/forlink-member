package com.danny.forlinkbackendspringboot.member;


import com.danny.forlinkbackendspringboot.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
@Table(name = "t_member")
public class Member extends BaseTimeEntity { // 회원 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_member_id")
    private Long memberId; // 회원 일련번호

    @Column(name = "t_nation_id")
    private Integer nationId; // 국가 일련번호

    @Column(name = "login_id")
    private String loginId; // 회원 로그인 아이디

    @Column(name = "password")
    private String password; // 회원 비밀번호

    @Column(name = "name")
    private String name; // 회원 이름

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private MemberRole role; // 회원 역할

}
