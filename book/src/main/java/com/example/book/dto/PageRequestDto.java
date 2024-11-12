package com.example.book.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
// 생성자를 만들었기 때문에 안씀
// import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// http://localhost:8080/book/list?page=2&sizw=20&type=c&keyword=소년

@ToString
@Builder
@AllArgsConstructor
@Setter
@Getter
public class PageRequestDto {
    private int page;
    private int size;

    // 검색
    private String type;
    private String keyword;

    public PageRequestDto() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }

}
