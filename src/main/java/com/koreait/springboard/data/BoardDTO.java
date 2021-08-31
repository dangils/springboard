package com.koreait.springboard.data;


import com.koreait.springboard.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor

public class BoardDTO {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private Long hit;
    private LocalDateTime regdate;

    public BoardEntity toEntity(){
        BoardEntity boardEntity = BoardEntity.builder()  //보드 엔티티 객체에 빌드하여 저장
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .hit(0L)
                .build();
        return boardEntity;
    }

    @Builder
    public BoardDTO(Long id, String title, String content ,String writer, Long hit, LocalDateTime regdate){
        this.id=id;
        this.title=title;
        this.writer=writer;
        this.content=content;
        this.regdate=regdate;
        this.hit=hit;
    }

}
