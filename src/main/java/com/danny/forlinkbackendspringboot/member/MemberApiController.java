package com.danny.forlinkbackendspringboot.member;

import com.danny.forlinkbackendspringboot.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<ApiResponse<MemberResponse>> save(@RequestBody @Valid MemberSaveRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(memberService.save(request)));
    }

    @PostMapping("/sessions")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody @Valid MemberLoginRequest request) {
        return ResponseEntity.status(201).body(new ApiResponse<>("저장 성공",201, memberService.login(request)));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<ApiResponse<MemberResponse>> findById(@PathVariable Long memberId) {
        return ResponseEntity.ok(new ApiResponse<>(memberService.findById(memberId)));
    }


}
