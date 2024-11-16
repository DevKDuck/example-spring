package com.devkduck.TipBoard.controller;


import com.devkduck.TipBoard.domain.TipBoard;
import com.devkduck.TipBoard.dto.TipRequestDTO;
import com.devkduck.TipBoard.service.TipBoardService;
import com.devkduck.common.CustomResponse;
import com.devkduck.user.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tip")
@RequiredArgsConstructor
public class TipBoardController {

    private final TipBoardService tipBoardService;

    @PostMapping("/insert")
    public CustomResponse<String> insertTip(@RequestBody TipRequestDTO tipRequestDTO, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Long userId = principalDetails.getUser().getId();
        String userName = principalDetails.getUser().getName();
        int result = tipBoardService.insertTip(tipRequestDTO,userId, userName);
        if (result >= 1) {
            return CustomResponse.ok("등록완료", null);
        }
        else{
            return CustomResponse.failure("등록실패");
        }
    }

    @GetMapping("/list")
    public CustomResponse listTip(){
        List<TipBoard> tipList = tipBoardService.getTipList();
        return CustomResponse.ok("꿀팁 게시물 조회 성공", tipList);
    }


    @GetMapping("/detail/{id}")
    public CustomResponse detailTip(@PathVariable Long id){
        TipBoard tip = tipBoardService.detailTip(id);
        return CustomResponse.ok("조회성공:" + id, tip);
    }

    @PutMapping("/update/{id}")
    public CustomResponse updateTip(@PathVariable Long id, @RequestBody TipRequestDTO tipRequestDTO){
        int success = tipBoardService.updateTip(id, tipRequestDTO);
        if(success >0){
            return CustomResponse.ok("수정성공", id);
        }
        else{
            return CustomResponse.failure("수정실패");
        }

    }
}
