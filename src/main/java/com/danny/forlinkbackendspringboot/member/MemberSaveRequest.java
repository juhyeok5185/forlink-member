package com.danny.forlinkbackendspringboot.member;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSaveRequest {

    @NotNull(message = "국가 ID는 필수입니다.")
    private Integer nationId;

    @NotNull(message = "로그인 ID는 필수입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "로그인 ID는 영문과 숫자만 사용할 수 있습니다.")
    @Size(min = 8, message = "로그인 ID는 최소 8글자 이상이어야 합니다.")
    private String loginId; // 회원 로그인 아이디

    @NotNull(message = "비밀번호는 필수입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).+$", message = "비밀번호는 영문과 숫자를 모두 포함해야 합니다.")
    @Size(min = 8, message = "비밀번호는 최소 8글자 이상이어야 합니다.")
    private String password; // 회원 비밀번호

    @NotNull(message = "이름은 필수입니다.")
    @Pattern(regexp = "^[가-힣]+$", message = "이름은 한글만 사용할 수 있습니다.")
    @Size(max = 5, message = "이름은 5글자 이하이어야 합니다.")
    private String name; // 회원 이름

    @NotNull
    private MemberRole role;

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .nationId(nationId)
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .role(role)
                .build();
    }
}
