package com.example.board.service;

import java.util.List;

import com.example.board.dto.ReplyDto;
import com.example.board.entity.Board;
import com.example.board.entity.Reply;

public interface ReplyService {

    Long register(ReplyDto dto);

    List<ReplyDto> list(Long bno);

    ReplyDto read(Long rno);

    Long modify(ReplyDto replyDto);

    void remove(Long rno);

    // entity => dto
    public default ReplyDto entityToDto(Reply entity) {
        ReplyDto dto = ReplyDto.builder()
                .rno(entity.getRno())
                .text(entity.getText())
                .replyer(entity.getReplyer())
                .bno(entity.getBoard().getBno())
                .regDate(entity.getRegDate())
                .updateDate(entity.getUpdateDate())
                .build();
        return dto;
    }

    // dto=> entity
    public default Reply dtoToEntity(ReplyDto dto) {
        Board board = Board.builder().bno(dto.getBno()).build();
        return Reply.builder()
                .rno(dto.getRno())
                .text(dto.getText())
                .replyer(dto.getReplyer())
                .board(board)
                .build();
    }
}