package com.devkduck.user.controller;


import com.devkduck.common.CustomResponse;
import com.devkduck.user.domain.UserRegisterDTO;
import com.devkduck.user.service.PrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user/auth")
@RequiredArgsConstructor
public class UserController {

    private final PrincipalDetailsService principalDetailsService;

    @PostMapping("/join")
    public CustomResponse<String> join(@Validated @RequestBody UserRegisterDTO userRegisterDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return CustomResponse.failure(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }else {
            int result = principalDetailsService.register(userRegisterDTO);
            if (result == 1) {
                System.out.println("등록되었습니다.");
                return CustomResponse.ok("등록 완료",null);
            } else {
                return CustomResponse.failure("등록 실패");
            }
        }
     }

    @GetMapping("/status")
    public CustomResponse<String> getLoginStatus() {
        // 현재 사용자의 인증 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 인증 정보가 있고, 인증된 상태인지 확인
        if (authentication != null) {
            System.out.println("null <<< 아직 존재해:" +authentication.getPrincipal());
            if (authentication.isAuthenticated()){
                System.out.println("아직 인증이 되어 있음");
            }
            // 로그인 상태이므로 사용자 정보를 반환 (예: 사용자명)
            String username = authentication.getName();
            return CustomResponse.ok("로그인 상태입니다.", username);
        } else {
            // 인증 정보가 없거나 인증되지 않은 경우, 로그아웃 상태로 간주
            return CustomResponse.failure("로그아웃 상태입니다.");
        }
    }

}
