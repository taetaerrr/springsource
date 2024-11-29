package com.example.movie.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MovieDto {

    private Long mno;

    @NotEmpty(message = "제목은 필수 요소 입니다.")
    private String title;

    // 영화에 소속된 이미지 가져오기
    @Builder.Default
    private List<MovieImageDto> movieImageDtos = new ArrayList<>();

    // 영화 평점 평균
    private double reviewAvg;

    // 영화 평점 개수
    private Long reviewCnt;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

}
