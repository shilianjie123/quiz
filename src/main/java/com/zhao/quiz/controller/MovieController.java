package com.zhao.quiz.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhao.quiz.domain.Movie;
import com.zhao.quiz.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Slf4j
@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping("/getAllMovie")
    public String getAllMovie(Model model,@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);//这行是重点，表示从pageNum页开始，每页pageSize条数据
        List<Movie> movieList = movieService.getAllMovie();
        PageInfo<Movie> pageInfo = new PageInfo<Movie>(movieList);
        List<String> queCourseList = movieService.getQueCourse();
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("queCourseList",queCourseList);
        return "movie/movieList";
    }
    //视频删除
    @RequestMapping("/deleteMovie/{id}")
    public String deleteMovieById(@PathVariable("id") Integer id) {
        movieService.deleteMovieById(id);
        return "redirect:/movie/getAllMovie";
    }

    //试题去修改页面
    @RequestMapping("/toEditMovie/{id}")
    public String toEditQuestion(@PathVariable("id") Integer id, Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "movie/movieEdit";
    }

    @RequestMapping("/EditMovie")
    public String editMovie(Movie movie){
        movieService.updateMovieById(movie);
        return "redirect:/movie/getAllMovie";
    }

    @RequestMapping("/toAddMovie")
    public String toAddMovie() {
        return "movie/movieAdd";
    }

    @RequestMapping("/AddMovie")
    public String addMoive(Movie movie){
        movieService.addMovie(movie);
        return "redirect:/movie/getAllMovie";
    }

    @ResponseBody
    @PostMapping("/upload")
    public String movieUpload(@RequestParam(value = "file") MultipartFile uploadFile) {
        long begin = System.currentTimeMillis();
        String json = "";
        try {
            Object result = movieService.uploadMovie(uploadFile);
            json = new ObjectMapper().writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("任务结束，共耗时：[" + (end - begin) + "]毫秒");
        return json;
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestParam MultipartFile file, @RequestParam String queCourse, HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        try {
            String fileName = file.getOriginalFilename();
            String extName = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = sdf.format(new Date()) + extName;
            File newFile = new File(request.getSession().getServletContext().getRealPath("/Users/shilianjie/video_test"));
            if (!newFile.exists()) newFile.mkdirs();
            newFile = new File(newFile, newFileName);
            OutputStream out = new FileOutputStream(newFile);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/movie/getAllMovie";
    }
}
