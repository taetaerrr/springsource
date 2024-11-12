package com.example.book.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.book.dto.BookDto;
import com.example.book.dto.CategoryDto;
import com.example.book.dto.PageRequestDto;
import com.example.book.dto.PageResultDto;
import com.example.book.dto.PublisherDto;
import com.example.book.entity.Book;
import com.example.book.service.BookService;

import jakarta.validation.Valid;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Log4j2
@RequestMapping("/book")
@Controller
public class BookController {

    private final BookService bookService;

    @GetMapping("/list")
    public void getList(PageRequestDto requestDto, Model model) {
        log.info("도서 전체 목록 요청");
        PageResultDto<BookDto, Book> result = bookService.getList(requestDto);
        model.addAttribute("result", result);
    }

    @GetMapping({ "/read", "/modify" })
    public void getRead(Long id, Model model) {
        log.info("book 상세 요청 {}", id);

        BookDto dto = bookService.getRow(id);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String postUpdate(BookDto dto, RedirectAttributes rttr) {
        log.info("도서 수정 요청 {}", dto);

        Long id = bookService.update(dto);

        // 상세조회로 이동
        rttr.addAttribute("id", id);
        return "redirect:read";
    }

    @PostMapping("/remove")
    public String postDelete(Long id, RedirectAttributes rttr) {
        log.info("메모 삭제 요청 {}", id);

        bookService.delete(id);

        rttr.addFlashAttribute("id", id + "번 도서가 삭제되었습니다.");
        return "redirect:list";
    }

    @GetMapping("/create")
    public void getCreate(@ModelAttribute("dto") BookDto dto, Model model) {
        log.info("도서 입력 폼 요청");

        List<CategoryDto> categories = bookService.getCateList();
        List<PublisherDto> publishdDtos = bookService.getPubList();

        model.addAttribute("cDtos", categories);
        model.addAttribute("pDtos", publishdDtos);

    }

    @PostMapping("/create")
    public String posCreate(@Valid @ModelAttribute("dto") BookDto dto, BindingResult result, Model model,
            RedirectAttributes rttr) {
        log.info("도서 입력 요청", dto);

        List<CategoryDto> categories = bookService.getCateList();
        List<PublisherDto> publishdDtos = bookService.getPubList();

        model.addAttribute("cDtos", categories);
        model.addAttribute("pDtos", publishdDtos);

        if (result.hasErrors()) {
            return "/book/create";
        }

        // 서비스 insert 호출
        Long id = bookService.create(dto);

        rttr.addFlashAttribute("msg", id + "번 도서가 등록되었습니다.");
        return "redirect:list";
    }

}
