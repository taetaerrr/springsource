package com.example.board.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.dto.ReplyDto;
import com.example.board.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor
@RestController
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/board/{bno}")
    public String getMethodName(@PathVariable Long bno) {
        log.info("{} 댓글 요청", bno);

        List<ReplyDto> replies = replyService.list(bno);
        return new String();
    }

}
