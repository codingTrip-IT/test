package com.example.testjdbc.controller;

import com.example.testjdbc.dto.MemoRequestDto;
import com.example.testjdbc.dto.MemoResponseDto;
import com.example.testjdbc.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memos")
    public MemoResponseDto save(MemoRequestDto dto){
        return memoService.save(dto);
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> findAll(){
        return memoService.findAll();
    }

    @GetMapping("/memos/{memoId}")
    public MemoResponseDto findById(@PathVariable Long memoId){
        return memoService.findById(memoId);
    }

    @PutMapping("/memos/{memoId}")
    public MemoResponseDto update(
            @PathVariable Long memoId,
            @RequestBody MemoRequestDto dto){
        return memoService.update(memoId,dto);
    }

    @DeleteMapping("/memos/{memoId}")
    public void delete(@PathVariable Long memoId){
        memoService.delete(memoId);
    }
}
