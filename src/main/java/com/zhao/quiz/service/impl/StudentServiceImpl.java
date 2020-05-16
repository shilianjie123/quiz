package com.zhao.quiz.service.impl;

import com.zhao.quiz.domain.Student;
import com.zhao.quiz.mapper.StudentMapper;
import com.zhao.quiz.service.StudentService;
import com.zhao.quiz.util.RandomNumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final static String PRE = "pre_";

    @Value("${spring.mail.username}")
    private String from;
    @Resource
    private JavaMailSender mailSender;
    @Value("${register.subject}")
    private String subject;
    @Value("${register.contentV1}")
    private String contentV1;
    @Value("${register.contentV2}")
    private String contentV2;
    @Autowired
    private RedisTemplate<String, String> rt;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getAll() {
        return studentMapper.queryAll();
    }

    @Override
    public Student check(Student student) {
        return studentMapper.check(student);
    }

    @Override
    public int queryCOuntALlstu() {
        return studentMapper.queryCOuntALlstu();
    }

    @Override
    public void AddStudent(Student student) {

        studentMapper.AddStudent(student);
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    public void EditStudent(Student student) {
        studentMapper.EditStudent(student);
    }

    @Override
    public void deleteById(Integer id) {
        studentMapper.deleteById(id);
    }

    /**
     * 发送邮件
     *
     * @param sendTo
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String sendTo, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sendTo);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(from);
        try {
            mailSender.send(message);
            logger.info("邮件已经发送出去");
        } catch (Exception e) {
            logger.error("邮件没有发送出去" + e);
        }
    }

    /**
     * 发送验证码
     */
    public void registerUser(String email) {
        long a = System.currentTimeMillis();
        //拼接key
        String key = PRE + email;
        String ranNumber = null;
        //判断key是否存在
        boolean isExist = rt.hasKey(key);
        //如果存在从redis拿取已有的
        if (isExist) {
            ranNumber = rt.opsForValue().get(key);
            //邮箱发送
            sendSimpleMail(email, subject, contentV2 + ranNumber);
        } else {
            //生成随机数
            ranNumber = RandomNumberUtil.getRandom();
            //邮箱发送
            sendSimpleMail(email, subject, contentV1 + ranNumber);
            ValueOperations forValue = rt.opsForValue();
            forValue.set(key, ranNumber);
            rt.expire(key, 120, TimeUnit.SECONDS);
        }
    }

    public RedisTemplate<String, String> getRt() {
        return rt;
    }

    @Override
    public List<String> getStudentAccount() {
        return studentMapper.getStudentAccount();
    }

}
