package com.example.board.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.entity.Reply;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsertMember() {

        IntStream.rangeClosed(1, 30).forEach(i -> {

            Member member = Member.builder()
                    .email("email " + i + "@gmail.com")
                    .name("name " + i)
                    .password("1111")
                    .build();
            memberRepository.save(member);
        });
    }

    @Test
    public void testInsertBoard() {
        IntStream.rangeClosed(1, 100).forEach(i -> {

            long num = (long) (Math.random() * 30) + 1;
            Member member = Member.builder().email("email " + num + "@gmail.com").build();

            Board board = Board.builder()
                    .content("content " + i)
                    .title("title " + i)
                    .writer(member)
                    .build();
            boardRepository.save(board);
        });
    }

    @Test
    public void testInsertReply() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            long num = (long) (Math.random() * 50) + 4;
            Board board = Board.builder().bno(num).build();

            Reply reply = Reply.builder()
                    .replyer("guest " + i)
                    .text("내용 " + i)
                    .board(board)
                    .build();
            replyRepository.save(reply);
        });
    }
}
