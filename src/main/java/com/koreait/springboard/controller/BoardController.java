package com.koreait.springboard.controller;

import com.koreait.springboard.data.BoardDTO;
import com.koreait.springboard.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller          // 뷰 페이지 호출
public class BoardController {

    private BoardService boardService;

    /* 게시판 목록 */
    @GetMapping("/")    // http://127.0.0.1:9090/
    public String list(Model model){
        List<BoardDTO> boardList = boardService.getBoardList();   //get리스트 한 후
        model.addAttribute("boardList", boardList);
                                     // 키값,         벨류값
        return "board/list.html";    // 해당 스트림을 찾아서 내보냄
    }

    @GetMapping("/write")
    public String write(){
        return "board/write.html";
    }

    @PostMapping("/post")
    public String write(BoardDTO boardDTO){
        boardService.savePost(boardDTO);  //dto에 각각의 데이터가 채워짐
        return "redirect:/";
    }



    @RequestMapping(value = "/detail/{no}", method= RequestMethod.GET)
    public String detail(@PathVariable("no") Long no, Model model){
        BoardDTO boardDTO = boardService.getPost(no);  //해당 번호에 대한 데이터 추출
        model.addAttribute("boardDTO",boardDTO);

        return "board/detail.html";
    }


    // http://127.0.0.1:9090/post/edit/1
    @RequestMapping(value = "/post/edit/{no}", method= RequestMethod.GET)
    public String edit(@PathVariable("no") Long no, Model model){
        BoardDTO boardDTO = boardService.getPost(no);
        model.addAttribute("boardDTO",boardDTO);
        return "board/edit.html";
    }

    @RequestMapping(value = "/post/edit/{no}", method= {RequestMethod.POST, RequestMethod.PUT})
    public String update(BoardDTO boardDTO){
        boardService.savePost(boardDTO);
        return "redirect:/";
    }

    @RequestMapping(value = "/post/{no}", method= {RequestMethod.POST, RequestMethod.DELETE})
    public String delete(@PathVariable("no") Long no){
        boardService.deletePost(no);
        return "redirect:/";
    }

    // axios를 사용한 리스트 페이지
    @GetMapping("/board/")
    public String boardList() {
        return "board/board_list.html";
    }



}
