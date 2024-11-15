package com.devkduck.user.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class TipController {

    @GetMapping("/tipForm")
    public String tipForm(){
        return "/tipForm";
    }

    @GetMapping("/tip/list")
    public String tipBoard(){
        return "/tipBoard";
    }

}
