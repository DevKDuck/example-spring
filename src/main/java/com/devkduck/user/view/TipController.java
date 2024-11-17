package com.devkduck.user.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


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
    @GetMapping("/tip/detail/{tipId}")
    public String tipDetail(@PathVariable("tipId") Long tipId, Model model){
        System.out.println(tipId);
        model.addAttribute("tipId", tipId);
        return "/tipDetail";
    }

    @GetMapping("/tip/update/{tipId}")
    public String tipUpdate(@PathVariable("tipId") Long tipId, Model model){
        System.out.println(tipId);
        model.addAttribute("tipId", tipId);
        return "/tipUpdate";
    }

}
