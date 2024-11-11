package com.example.memo.repository;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.memo.entity.Memo;

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    private MemoRepository memoRepository;

    @Test
    public void testMemoInsert() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Memo memo = Memo.builder().memoText("memo text" + i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testMemoRead() {
        // 6번 메모 가져오기
        Memo memo = memoRepository.findById(6L).get();
        System.out.println(memo);

        System.out.println();

        // 전체 메모 가져오기
        List<Memo> list = memoRepository.findAll();
        list.forEach(m -> System.out.println(memo));
    }

    @Test
    public void testMemoUpdate() {

        // 7번 메모 내용 수정
        Memo memo = memoRepository.findById(7L).get();
        memo.setMemoText("내용 수정");
        memoRepository.save(memo);
    }

    @Test
    public void testMemoDelete() {

        // 메모삭제
        memoRepository.deleteById(4L);
    }

    @Test
    public void testMnoList() {
        // memoRepository.findByMnoLessThan(5L).forEach(b -> System.out.println(b));

        // memoRepository.findByMnoLessThanOrderByMnoDesc(10L).forEach(b ->
        // System.out.println(b));

        memoRepository.findByMnoBetweenOrderByMnoDesc(20L, 100L).forEach(b -> System.out.println(b));
    }
}
