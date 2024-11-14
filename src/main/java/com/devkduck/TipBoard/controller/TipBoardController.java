package com.devkduck.TipBoard.controller;


import com.devkduck.TipBoard.dto.TipRequestDTO;
import com.devkduck.TipBoard.service.TipBoardService;
import com.devkduck.common.CustomResponse;
import com.devkduck.user.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
}
