package com.zhao.quiz.service.impl;

import com.zhao.quiz.domain.Movie;
import com.zhao.quiz.mapper.MovieMapper;
import com.zhao.quiz.service.MovieService;
import com.zhao.quiz.util.FtpUtil;
import com.zhao.quiz.util.IDUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public List<Movie> getAllMovie() {
        return movieMapper.getAllMovie();
    }

    @Override
    public void addMovie(Movie movie) {
        movieMapper.addMovie(movie);
    }

    @Override
    public List<String> getQueCourse() {
        return movieMapper.getQueCourse();
    }

    @Override
    public void deleteMovieById(Integer id) {
        movieMapper.deleteMovieById(id);
    }

    @Override
    public void updateMovieById(Movie movie) {
        movieMapper.updateMovieById(movie);
    }

    @Override
    public Movie getMovieById(Integer id) {
        return movieMapper.getMovieById(id);
    }

    @Override
    public Object uploadMovie(MultipartFile uploadFile) {
        //1、给上传的图片生成新的文件名
        //1.1获取原始文件名
        String oldName = uploadFile.getOriginalFilename();
        //1.2使用IDUtils工具类生成新的文件名，新文件名 = newName + 文件后缀
        String newName = IDUtil.genImageName();
        assert oldName != null;
        newName = newName + oldName.substring(oldName.lastIndexOf("."));
        //1.3生成文件在服务器端存储的子目录
        String filePath = new DateTime().toString("/yyyyMMdd/");

        //2、把图片上传到图片服务器
        //2.1获取上传的io流
        InputStream input = null;
        try {
            input = uploadFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //2.2调用FtpUtil工具类进行上传
        return FtpUtil.putMovie(input, filePath, newName);
    }


}
