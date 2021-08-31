package com.koreait.springboard.controller;

import com.koreait.springboard.data.BoardDTO;
import com.koreait.springboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController    //api 호출시 json으로 출력
public class BoardRestController {
    @Autowired
    BoardService boardService;


    //게시판 리스트
    @GetMapping("/api/list")
    public List<BoardDTO> list(){
        return boardService.getBoardList();
    }

    //게시판 글작성
    @PostMapping("/api/write")
    public void write(@RequestBody BoardDTO boardDTO){
       boardService.insertPost(boardDTO);
    }


    // 게시판 글확인
    @GetMapping("/api/{id}")
    public BoardDTO getPost(@PathVariable Long id){
       return boardService.getData(id);
    }

    // 게시판 글수정
    @PutMapping("/api/{id}")
    public void updatePost(@PathVariable Long id, @RequestBody BoardDTO boardDTO){
        boardService.updatePost(id,boardDTO);

    }


    // 게시판 글삭제
    @DeleteMapping("/api/{id}")
    public void deletePost(@PathVariable Long id){
        boardService.delPost(id);
    }

}
