package com.danny.forlinkbackendspringboot.member;

import com.danny.forlinkbackendspringboot.common.config.MyException;
import com.danny.forlinkbackendspringboot.common.util.AES256Utils;
import com.danny.forlinkbackendspringboot.common.util.JWTUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberReader memberReader;
    private final MemberStore memberStore;
    private final PasswordEncoder passwordEncoder;
    private final MemberFactory memberFactory;

    @Transactional
    public MemberResponse save(MemberSaveRequest request) {
        Member member = memberFactory.createMember(request);
        saveValidation(member);
        return memberFactory.createMemberResponse(memberStore.save(member));
    }

    @Transactional
    public String login(@Valid MemberLoginRequest request) {
        Member member = memberReader.findByLoginId(AES256Utils.encrypt(request.getLoginId()));
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new MyException("비밀번호가 틀렸습니다.");
        }
        return "Bearer " + JWTUtils.generateToken(member.getLoginId(), member.getRole().name());
    }

    @Transactional(readOnly = true)
    public MemberResponse findById(Long memberId) {
        return memberFactory.createMemberResponse(memberReader.findById(memberId));
    }

    public void saveValidation(Member member) {
        Optional.ofNullable(memberReader.findByLoginId(member.getLoginId()))
                .ifPresent(existing -> {
                    throw new MyException("이미 등록된 아이디 입니다.");
                });
    }
}
