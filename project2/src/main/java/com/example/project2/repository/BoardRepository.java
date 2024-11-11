package com.example.project2.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project2.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // where title = ?
    // List<Board> findByTitle(String title);

    // where title like ?
    // List<Board> findByTitleLike(String title);

    // where title like '%title'
    // List<Board> findByTitleStartingWith(String title);

    // where writer like 'writer%'
    // List<Board> findByWriterEndingWith(String writer);

    // where writer like '%writer%'
    // List<Board> findByWriterContaining(String writer);

    // where writer like '%writer%' or title like '%title%'
    // List<Board> findByWriterContainingOrTitleContaining(String writer, String
    // title);

    // where writer like '%writer%' and id > 10'
    // List<Board> findByWriterContainingAndGreaterThan(String writer, Long id);

    // id > 10 order by id desc
    // List<Board> findByIdGreaterThanOrderByIdDesc(Long id);

    // List<Board> findByIdGreaterThanOrderByIdDesc(Long id, Pageable pageable);

    // select * from board b where b.writer like '%user%' and b.id > 0 order by b.id
    // desc
    // @Query("SELECT B FROM BOARD B where b.writer LIKE %:writer% and b.id > 0
    // ORDER BY b.id DESC")
    @Query("SELECT B FROM BOARD B where b.writer LIKE %?1% and b.id > 0 ORDER BY b.id DESC")
    List<Board> findByWriterList(String writer);

    // @Query("SELECT B FROM BOARD B where b.title = :title")
    // @Query("SELECT B FROM BOARD B where b.title Like %:title%")
    @Query("SELECT B FROM BOARD B where b.title Like %?1%")
    List<Board> findByTitle(String title);

    @Query("SELECT B FROM BOARD B where b.writer like %?1% or b.tilte Like %?2%")
    List<Board> findByWriterContainingOrTitleContaining(String writer, String title);
}
