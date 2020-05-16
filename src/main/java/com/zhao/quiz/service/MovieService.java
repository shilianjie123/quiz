package com.zhao.quiz.service;

import com.zhao.quiz.domain.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovie();

    void addMovie(Movie movie);

    List<String> getQueCourse();

    void deleteMovieById(Integer id);

    void updateMovieById(Movie movie);

    Movie getMovieById(Integer id);

    Object uploadMovie(MultipartFile multipartFile);

}
