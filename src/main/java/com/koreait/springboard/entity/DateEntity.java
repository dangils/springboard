package com.koreait.springboard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass  // 자식 클래스에게 매핑정보를 상속하기 위한 어노테이션
@EntityListeners(AuditingEntityListener.class)  // JPA에게 해당 엔티티는 auditing 기능을 사용 (자동으로 들어가는 데이터)
public abstract class DateEntity { //abstract 이므로 builder 불가능
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regdate;





}
