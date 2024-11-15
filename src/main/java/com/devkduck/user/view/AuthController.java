package com.devkduck.user.view;

import com.devkduck.user.auth.PrincipalDetails;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AuthController {
    @GetMapping("/loginForm")
    public String loginForm(Model model, @AuthenticationPrincipal PrincipalDetails userDetails){
        //login 했으면 접근 못하도록 리다이렉션

        // 인증된 사용자가 있다면 /home으로 리디렉션
        if (userDetails != null) {
            model.addAttribute("email", userDetails.getUsername());
//            System.out.println(authentication.getPrincipal());
            return "redirect:/"; // 이미 로그인된 사용자라면 홈으로 리디렉션
        }
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

    @GetMapping("/")
    public String home(){return "index";}

}
