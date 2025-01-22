package com.danny.forlinkbackendspringboot.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String message;  // 응답 메시지
    private int status;      // HTTP 상태 코드
    private T data;          // 응답 데이터

    public ApiResponse(T data) {
        this.message = "Api Success";
        this.status = 200;
        this.data = data;
    }

    // 성공 응답을 생성하는 정적 메서드
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(message, 200, data);
    }

    // 실패 응답을 생성하는 정적 메서드
    public static <T> ApiResponse<T> failure(String message, int status) {
        return new ApiResponse<>(message, status, null);
    }
}
