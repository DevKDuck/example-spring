package com.devkduck.user.view;

import com.devkduck.user.auth.PrincipalDetails;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;


@Controller
public class AuthController {
    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }

    //일반 로그인
    @GetMapping("/test/login")
    public @ResponseBody String testLogin(Authentication authentication, @AuthenticationPrincipal PrincipalDetails userDetails) {
        PrincipalDetails principalDeatils = (PrincipalDetails)authentication.getPrincipal(); //Object로 받아서 다운캐스팅
        System.out.println("일반 로그인 Authentication" + principalDeatils.getUser());

        System.out.println("일반 로그인 @AuthenticationPrincipal" + userDetails.getUser());
        return "" + principalDeatils.getUser();
    }
}
