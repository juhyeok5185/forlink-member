package com.danny.forlinkbackendspringboot.member;

import com.danny.forlinkbackendspringboot.common.encrypt.Encryptor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberFactory {

    private final PasswordEncoder passwordEncoder;
    private final Encryptor encryptor;

    public Member createMember(MemberSaveRequest request) {
        return Member.builder()
                .nationId(request.getNationId())
                .loginId(encryptor.encrypt(request.getLoginId()))
                .password(passwordEncoder.encode(request.getPassword()))
                .name(encryptor.encrypt(request.getName()))
                .role(request.getRole())
                .build();
    }

    public MemberResponse createMemberResponse(Member member) {
        return MemberResponse.builder()
                .memberId(member.getMemberId())
                .nationId(member.getNationId())
                .loginId(encryptor.decrypt(member.getLoginId()))
                .name(encryptor.decrypt(member.getName()))
                .build();
    }
}
