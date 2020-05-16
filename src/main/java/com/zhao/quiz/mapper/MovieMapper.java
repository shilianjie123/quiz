package com.zhao.quiz.mapper;

import com.zhao.quiz.domain.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface MovieMapper {
    List<Movie> getAllMovie();

    void addMovie(Movie movie);

    List<String> getQueCourse();

    void deleteMovieById(Integer id);

    void updateMovieById(Movie movie);

    Movie getMovieById(Integer id);

}
