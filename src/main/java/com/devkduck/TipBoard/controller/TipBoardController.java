package com.devkduck.TipBoard.controller;


import com.devkduck.TipBoard.domain.TipBoard;
import com.devkduck.TipBoard.dto.TipRequestDTO;
import com.devkduck.TipBoard.dto.TipSearchDto;
import com.devkduck.TipBoard.service.TipBoardService;
import com.devkduck.common.CustomResponse;
import com.devkduck.user.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public CustomResponse detailTip(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails){
        TipBoard tip = tipBoardService.detailTip(id);
        try{
            Long userId = principalDetails.getUser().getId();

            if(userId.equals(tip.getUserId())){
                return CustomResponse.ok("작성자와 사용자 일치", tip);
            }else{
                return CustomResponse.forbidden("작성자와 사용자 불일치", tip);
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return CustomResponse.unauthorized("로그인 하지 않은 사용자", tip);
        }


    }


    @PutMapping("/update/{id}")
    public CustomResponse updateTip(@PathVariable Long id, @RequestBody TipRequestDTO tipRequestDTO) {
        System.out.println(tipRequestDTO.getTipTitle());
        int success = tipBoardService.updateTip(id, tipRequestDTO);
        if(success >0){
            return CustomResponse.ok("수정성공", id);
        }
        else{
            return CustomResponse.failure("수정실패");
        }

    }
    @DeleteMapping("/delete/{id}")
    public CustomResponse deleteTip(@PathVariable Long id){
        int success = tipBoardService.deleteTip(id);
        if (success >0){
            return CustomResponse.ok("삭제 성공",id);
        }
        else{
            return CustomResponse.failure("삭제 실패");
        }
    }

    //pageable test
    @GetMapping("/page")
    public CustomResponse getListBoards(TipBoard tipBoard, @PageableDefault(size = 10 ) Pageable pageable){
        return CustomResponse.ok("페이징 ", tipBoardService.getListBoard(tipBoard,pageable));
    }

    //search
    @GetMapping("/search")
    public CustomResponse searchTip(TipSearchDto tipSearchDto){
        return CustomResponse.ok("검색 성공", tipBoardService.searchTip(tipSearchDto));
    }

}
