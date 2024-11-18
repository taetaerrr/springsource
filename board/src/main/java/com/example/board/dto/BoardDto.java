package com.example.board.dto;

import java.time.LocalDateTime;

import com.example.board.entity.Member;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDto {
    private Long bno;

    @NotBlank(message = "title 은 필수 입력 요소입니다.")
    private String title;

    @NotBlank(message = "content 은 필수 입력 요소입니다.")
    private String content;

    // private Member member;
    // private String email;
    // private String name;

    @NotBlank(message = "writerEmail 은 필수 입력 요소입니다.")
    private String writerEmail;

    private String writerName;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    // 댓글 개수
    private Long replyCnt;
}
