package com.devkduck.user.view;

import com.devkduck.TipBoard.dto.TipRequestDTO;
import com.devkduck.TipBoard.service.TipBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller

public class TipController {


    @GetMapping("/tip/list")
    public String tipBoard(){
        return "/tipBoard";
    }

    @GetMapping("/tipForm")
    public String tipForm(){
        return "/tipForm";
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
        return "/tipUpdateForm";
    }

    @GetMapping("/tip/page")
    public String pageBoard(){
        return "/pageBoard";
    }

    @GetMapping("/tip/search")
    public String search(){
        return "/searchBoard";
    }

}
