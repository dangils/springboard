package com.koreait.springboard.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
//(access = AccessLevel.PROTECTED)

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@SequenceGenerator(
        name="seq_springboard_idx",
        sequenceName = "seq_springboard_idx",
        initialValue = 1,
        allocationSize = 1
)

@Table(name="springboard")
public class BoardEntity extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_springboard_idx")
    private Long id;

    private String writer;
    private String title;
    private String content;
    private Long hit;


    @Builder
    public BoardEntity(Long id, String writer, String title, String content, Long hit, LocalDateTime regdate){
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.hit = hit;

    }

}
