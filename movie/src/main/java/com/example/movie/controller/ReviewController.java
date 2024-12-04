package com.example.movie.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.movie.dto.ReviewDto;
import com.example.movie.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RequestMapping("/reviews")
@RequiredArgsConstructor
@Log4j2
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    // ~~ /reviews/45/all
    @GetMapping("/{mno}/all")
    public List<ReviewDto> geList(@PathVariable Long mno) {
        log.info("리뷰 리스트 요청 {}", mno);

        List<ReviewDto> reviews = reviewService.getReviews(mno);

        return reviews;
    }

    // ~~/reviews/mno/reviewno
    @DeleteMapping("/{mno}/{reviewNo}")
    public Long postMethodName(@PathVariable Long reviewNo) {
        log.info("리뷰 삭제 {}", reviewNo);

        reviewService.removeReview(reviewNo);

        return reviewNo;
    }

    // ~~/reviews/mno/reviewno + @GetMapping
    @GetMapping("/{mno}/{reviewNo}")
    public ReviewDto getRead(@PathVariable Long reviewNo) {

        log.info("리뷰 상세 조회 {}", reviewNo);

        ReviewDto reviewDto = reviewService.getReview(reviewNo);

        return reviewDto;
    }

    // ~~/reviews/mno/reviewno + @putmapping +ReviewDto
    @PreAuthorize("autentication.name == #reviewDto.email")
    @PutMapping("/{mno}/{reviewNo}")
    public Long putMethodName(@PathVariable Long reviewNo, @RequestBody ReviewDto reviewDto) {
        log.info("리뷰 수정 {} {}", reviewNo, reviewDto);

        reviewDto.setReviewNo(reviewNo);
        reviewNo = reviewService.modifyReview(reviewDto);

        return reviewNo;
    }

    @PostMapping("/{mno}")
    public Long postMethodName(@RequestBody ReviewDto reviewDto) {
        log.info("리뷰 작성 {}", reviewDto);

        return reviewService.addReview(reviewDto);

    }

}