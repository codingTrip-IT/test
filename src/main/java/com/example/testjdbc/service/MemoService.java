package com.example.testjdbc.service;

import com.example.testjdbc.dto.MemoRequestDto;
import com.example.testjdbc.dto.MemoResponseDto;
import com.example.testjdbc.entity.Memo;
import com.example.testjdbc.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponseDto save(MemoRequestDto dto){
        Memo memo = new Memo(dto.getContent());
        Memo savedMemo = memoRepository.save(memo);
        return new MemoResponseDto(savedMemo.getId(),savedMemo.getContent());
    }

    @Transactional(readOnly = true)
    public List<MemoResponseDto> findAll(){
        List<Memo> memos = memoRepository.findAll();
        List<MemoResponseDto> dtos = new ArrayList<>();

        for (Memo memo : memos) {
            MemoResponseDto dto = new MemoResponseDto(memo.getId(), memo.getContent());
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public MemoResponseDto findById(Long id){
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 존재하지 않습니다.")
        );
        return new MemoResponseDto(memo.getId(),memo.getContent());
    }

    @Transactional
    public MemoResponseDto update(Long id, MemoRequestDto dto){
        Memo updatedMemo = memoRepository.updateContent(id, dto.getContent());
        return new MemoResponseDto(updatedMemo.getId(), updatedMemo.getContent());
    }

    @Transactional
    public void delete(Long id){
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id가 존재하지 않습니다.")
        );
        memoRepository.deleteById(id);
    }

}
