package com.example.movie.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.movie.dto.MovieDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.dto.PageResultDto;
import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;
import com.example.movie.repository.MovieImageRepository;
import com.example.movie.repository.MovieRepository;
import com.example.movie.repository.ReviewRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieImageRepository movieImageRepository;

    private final MovieRepository movieRepository;

    private final ReviewRepository reviewRepository;

    @Override
    public PageResultDto<MovieDto, Object[]> getList(PageRequestDto pageRequestDto) {

        Pageable pageable = pageRequestDto.getPageable(Sort.by("mno").descending());

        Page<Object[]> result = movieImageRepository.getTotalList(null, null, pageable);

        Function<Object[], MovieDto> function = (en -> entityToDto((Movie) en[0],
                (List<MovieImage>) Arrays.asList((MovieImage) en[1]),
                (Long) en[2], (Double) en[3]));

        return new PageResultDto<>(result, function);
    }

    @Override
    public Long register(MovieDto movieDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    public Long modify(MovieDto movieDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modify'");
    }

    @Transactional
    @Override
    public void delete(Long mno) {
        Movie movie = Movie.builder().mno(mno).build();

        movieImageRepository.deleteByMovie(movie);

        reviewRepository.deleteByMovie(movie);

        movieRepository.delete(movie);
    }

    @Override
    public MovieDto get(Long mno) {
        List<Object[]> result = movieImageRepository.getMovieRow(mno);

        Movie movie = (Movie) result.get(0)[0];
        Long reviewCnt = (Long) result.get(0)[2];
        Double avg = (Double) result.get(0)[3];

        // [1] : 영화 이미지
        List<MovieImage> movieImages = new ArrayList<>();
        // result 에서 한 행씩 가져오기
        result.forEach(row -> {
            MovieImage movieImage = (MovieImage) row[1];
            movieImages.add(movieImage);
        });

        return entityToDto(movie, movieImages, reviewCnt, avg);

        // [Movie(mno=1, title=Movie 1), MovieImage(inum=5,
        // uuid=c957b1eb-636d-404e-bae0-99d6315e6ef7, imgName=test4.jpg, path=null), 4,
        // 3.0]
        // [Movie(mno=1, title=Movie 1), MovieImage(inum=4,
        // uuid=ac3c2238-8622-4034-a0a9-1a27d11472f2, imgName=test3.jpg, path=null), 4,
        // 3.0]
        // [Movie(mno=1, title=Movie 1), MovieImage(inum=3,
        // uuid=b2d83489-7efa-4e4a-b38a-af1e84f17483, imgName=test2.jpg, path=null), 4,
        // 3.0]
        // [Movie(mno=1, title=Movie 1), MovieImage(inum=2,
        // uuid=8b93b3d7-7601-46bb-b613-c163f1da6d8f, imgName=test1.jpg, path=null), 4,
        // 3.0]
        // [Movie(mno=1, title=Movie 1), MovieImage(inum=1,
        // uuid=74acbd30-4b69-4596-9e98-57793a9e059c, imgName=test0.jpg, path=null), 4,
        // 3.0]

    }

}
