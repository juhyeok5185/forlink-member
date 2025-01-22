package com.danny.forlinkbackendspringboot.nation;

import com.danny.forlinkbackendspringboot.common.response.ApiResponse;
import com.danny.forlinkbackendspringboot.common.util.JWTUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/nation")
public class NationApiController {

    private final NationService nationService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<NationResponse>> save(@RequestBody @Valid NationRequest request){
        return ResponseEntity.ok(new ApiResponse<>(nationService.save(request)));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<List<NationResponse>>> findAll(Authentication authentication){
        return ResponseEntity.ok(new ApiResponse<>(nationService.findAll()));
    }

    @GetMapping("/{nationId}")
    public ResponseEntity<ApiResponse<NationResponse>> findById(@PathVariable Integer nationId){
        return ResponseEntity.ok(new ApiResponse<>(nationService.findById(nationId)));
    }

    @PatchMapping("/{nationId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<NationResponse>> update(@PathVariable Integer nationId, @RequestBody @Valid NationRequest request){
        return ResponseEntity.ok(new ApiResponse<>(nationService.update(nationId, request)));
    }

    @DeleteMapping("/{nationId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable Integer nationId){
        return ResponseEntity.ok(new ApiResponse<>(nationService.delete(nationId)));
    }

}
