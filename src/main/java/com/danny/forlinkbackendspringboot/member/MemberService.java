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
    private final PasswordEncoder passwordEncoder;

    public MemberResponse save(MemberSaveRequest request) {
        return modelMapper.map(memberStore.save(request.toEntity(passwordEncoder)), MemberResponse.class);
    }

    public String login(@Valid MemberLoginRequest request) {
        Member member = memberReader.findByLoginId(AES256Utils.encrypt(request.getLoginId()));
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new MyException("비밀번호가 틀렸습니다.");
        }
        return "Bearer " + JWTUtils.generateToken(member.getLoginId(), member.getRole().name());
    }

    public MemberResponse findById(Long memberId) {
        return new MemberResponse(memberReader.findById(memberId));
    }
}
