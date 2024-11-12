package com.example.book.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import groovy.transform.builder.Builder;
import lombok.Data;

//  Page<Book> result 결과를 담는 Dto
// Entity ==> Dto : result.getContent() ==> List<BookDto> 변경

@Builder
@Data
public class PageResultDto<Dto, EN> {

    // 화면에 보여줄 목록
    private List<Dto> dtoList;

    // 총 페이지 번호
    private int totalPage;

    // 현재 페이지 번호
    private int page;

    // 목록 사이즈
    private int size;

    // 시작페이지, 끝페이지 번호
    private int start, end;

    // 이전, 다음 여부
    private boolean prev, next;

    // 화면에 보여줄 페이지 번호 목록
    private List<Integer> pageList;

    public PageResultDto(Page<EN> result, Function<EN, Dto> fn) {

        // List<Book> books = result.getContent();
        // List<BookDto> list = books.stream().map(b ->
        // entityTodoDto(b)).collect(Collectors.toList());

        dtoList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages();
        makePredicate(result.getPageable());
    }

    private void makePredicate(Pageable pageable) {
        // 사용자가 요청한 페이지 번호
        // 0
        this.page = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();

        int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;

        this.start = tempEnd - 9;
        this.prev = this.start > 1;
        this.end = totalPage > tempEnd ? tempEnd : totalPage;
        this.next = totalPage > tempEnd;

        // List<Integer> list = new ArrayList<>();
        // list.add(null);

        pageList = IntStream.rangeClosed(start, end)
                .boxed() // int ==> Integer int로는 list를 만들수 없기 때문 객체로만 가능
                .collect(Collectors.toList());
    }
}
