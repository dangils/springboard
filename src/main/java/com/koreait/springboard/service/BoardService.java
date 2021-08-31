package com.koreait.springboard.service;

import com.koreait.springboard.data.BoardDTO;
import com.koreait.springboard.entity.BoardEntity;
import com.koreait.springboard.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BoardService {

    private BoardRepository boardRepository;

    // 리스트   (getList 할때 가져오는 데이터)
    @Transactional    // 데이터를 순차적으로 계산하기 위한 어노테이션, 쓰레드의 꼬임 방지
    public List<BoardDTO> getBoardList() {
        List<BoardEntity> boardEntities = boardRepository.findAll(); //jpa에 의해 가져온 데이터
           // 데이터를 모두 가져와, boardEntities 에 저장(리스트 형태로), 보드 entity가 여러개 모여잇는 형태 (여러 라인이 저장)
            //entity 데이터의 한줄 한줄,
        List<BoardDTO> boardDTOList = new ArrayList<>();
            //가져온 boardEntities를 저장하기 위한 array리스트 객체 생성
        for(BoardEntity boardEntity : boardEntities){

            BoardDTO boardDTO = BoardDTO.builder()  //dto에 데이터를 모두 넣어줌
                    .id(boardEntity.getId())
                    .title(boardEntity.getTitle())
                    .content(boardEntity.getContent())
                    .writer(boardEntity.getWriter())
                    .hit(boardEntity.getHit())
                    .regdate(boardEntity.getRegdate())
                    .build();
            boardDTOList.add(boardDTO);   //arraylist에 데이터 저장
        }
        return boardDTOList;  //arraylist에 담은것을리턴
    }

    // 글보기
    public BoardDTO getPost(Long id){
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityOptional.get();

        BoardDTO boardDTO = BoardDTO.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .hit(boardEntity.getHit())
                .regdate(boardEntity.getRegdate())
                .build();
        return boardDTO;
    }

    // 글저장
    public Long savePost(BoardDTO boardDTO){   // dto를 전달 받아
        return boardRepository.save(boardDTO.toEntity()).getId();
                            //jpa 메소드 save를 사용하여 레퍼지토리에 저장된 후, nextval로 getID 추출하여 리턴
    }

    // 글삭제
    public void deletePost(Long id){
        boardRepository.deleteById(id);
    }

    //REST 방식 글저장
    @Transactional
    public void insertPost(BoardDTO boardDTO){
        boardRepository.save(boardDTO.toEntity());
    }

    //Rest 방식 글보기
    @Transactional
    public BoardDTO getData(Long id){
       Optional<BoardEntity> boardEntityOptional = boardRepository.findById(id);
       BoardEntity boardEntity = boardEntityOptional.get();

       return BoardDTO.builder()
               .id(boardEntity.getId())
               .title(boardEntity.getTitle())
               .content(boardEntity.getContent())
               .writer(boardEntity.getWriter())
               .hit(boardEntity.getHit())
               .regdate(boardEntity.getRegdate())
               .build();
    }

    //Rest 글 수정
    @Transactional
    public void updatePost(Long id, BoardDTO boardDTO){
        Optional<BoardEntity> boardEntity = boardRepository.findById(id);

        boardEntity.ifPresent(select -> {
            select.setTitle(boardDTO.getTitle());
            select.setContent(boardDTO.getContent());
            select.setWriter(boardDTO.getWriter());
        });
    }

    //Rest 글 삭제

    @Transactional
    public void delPost(Long id){
        boardRepository.deleteById(id);
    }


}
