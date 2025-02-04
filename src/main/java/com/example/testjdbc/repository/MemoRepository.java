package com.example.testjdbc.repository;

import com.example.testjdbc.entity.Memo;

import java.util.List;
import java.util.Optional;

public interface MemoRepository {

    Memo save(Memo memo);
    Optional<Memo> findById(Long id);
    List<Memo> findAll();
    Memo updateContent(Long id, String content);
    void deleteById(Long id);
}


