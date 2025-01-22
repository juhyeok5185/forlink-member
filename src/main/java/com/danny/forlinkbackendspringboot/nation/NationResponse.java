package com.danny.forlinkbackendspringboot.nation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class NationResponse {
    private Integer notionId; // 회원 일련번호
    private String code; // 회원 로그인 아이디
    private String name; // 회원 이름

    public NationResponse(Nation nation) {
        this.notionId = nation.getNotionId();
        this.code = nation.getCode();
        this.name = nation.getName();
    }
}
