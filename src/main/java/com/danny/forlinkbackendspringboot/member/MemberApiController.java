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

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<MemberResponse>> save(@RequestBody @Valid MemberRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(memberService.save(request)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody @Valid MemberAuthRequest request) {
        String token = memberService.login(request);
        return ResponseEntity.ok(new ApiResponse<>(token));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<ApiResponse<MemberResponse>> findById(@PathVariable Long memberId) {
        return ResponseEntity.ok(new ApiResponse<>(memberService.findById(memberId)));
    }


}
