package com.devkduck.user.view;

import com.devkduck.user.auth.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TipController {

    @GetMapping("/tipForm")
    public String tipForm(){
        return "/tipForm";
    }

    @GetMapping("/tipBoard")
    public String tipBoard(){
        return "/tipBoard";
    }

}
