package com.danny.forlinkbackendspringboot.nation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NationRequest {

    @NotNull
    @Size(max = 2, message = "코드는 최대 2글자여야 합니다.")
    private String code;

    @NotNull
    @Size(max = 10, message = "이름은 최대 10글자여야 합니다.")
    private String name;
}
