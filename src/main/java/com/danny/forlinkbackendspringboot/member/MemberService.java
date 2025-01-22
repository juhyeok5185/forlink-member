package com.danny.forlinkbackendspringboot.member;

import com.danny.forlinkbackendspringboot.common.config.MyException;
import com.danny.forlinkbackendspringboot.common.util.AES256Utils;
import com.danny.forlinkbackendspringboot.common.util.JWTUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberReader memberReader;
    private final MemberStore memberStore;
    private final ModelMapper modelMapper;
//    private final NationProducer nationProducer;
    private final PasswordEncoder passwordEncoder;

    public MemberResponse save(MemberRequest request) {
        Member member = Member.builder()
                .loginId(AES256Utils.encrypt(request.getLoginId()))
                .password(passwordEncoder.encode(request.getPassword()))
                .name(AES256Utils.encrypt(request.getName()))
                .role(request.getRole())
                .nationId(request.getNationId())
                .build();
        memberStore.save(member);
        return modelMapper.map(member, MemberResponse.class);
    }

    public String login(@Valid MemberAuthRequest request) {
        String encryptLoginId = AES256Utils.encrypt(request.getLoginId());
        Member member = memberReader.findByLoginId(encryptLoginId);
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new MyException("비밀번호가 틀렸습니다.");
        }
        return "Bearer " + JWTUtils.generateToken(member.getLoginId(), member.getRole().name());
    }

    public MemberResponse findById(Long memberId) {
        Member member = memberReader.findById(memberId);
        return MemberResponse.builder()
                .memberId(member.getMemberId())
                .nationId(member.getNationId())
                .loginId(AES256Utils.decrypt(member.getLoginId()))
                .name(AES256Utils.decrypt(member.getName()))
                .build();
    }
}
