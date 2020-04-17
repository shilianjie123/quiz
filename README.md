介绍：
----
*  项目名称：`在线考试系统`
*  开发环境：Tomcat9.0 Mysql5.17 springboot、springmvc、mybatis、thymeleaf、bootstrap、maven、druid、jquery、ajax、layer、lombok、              devtools、pagehelper 
*  开发工具：Navicat+Idea
*  项目描述： 这个项目是我的毕业设计，前后一个月完成（2020年1月）。该项目是一个基于springboot快速集成SSM搭建的在线考试的系统，实现在线考试，后台出题、前台考试、自动阅卷。分前台考试和后台管理；前台是学生注册登录进入，有两个功能模块一个是考试，另一个是记录。后台是分管理员和教师两种权限登录，后台有学生管理、教师管理、班级管理、试题管理、试卷管理、考试管理、成绩管理几大功能模块；项目特色：试题可以分类（例如java类多选题）、分页查看；同样可以分类为试卷添加试题，也可以人工组题和随机出题（随机批量添加），以及批量移除。实现自动化无纸化考试

截图：
----
<img src="https://github.com/shuzhao11/quiz/blob/master/images/Image.png" width="1080" heigh="720"/>
<img src="https://github.com/shuzhao11/quiz/blob/master/images/Image.png1" width="1080" heigh="720"/>
<img src="https://github.com/shuzhao11/quiz/blob/master/images/Image.png2" width="1080" heigh="720"/>
<img src="https://github.com/shuzhao11/quiz/blob/master/images/Image.png3" width="1080" heigh="720"/>

提示：
----
*  1.缺少数据库文件。提示：数据库可以根据javabean和mapper文件创建出来，共9张表classe（班级）、exam（考试）、paper（试卷）、question（试题）、question_paper(中间表）、record（记录）、student（学生表）、teacher（教师表）、toscore（总分表）。
*  2.teacher和student表添加数据用户自行添加登录系统localhost：8080自动跳转index页面。question必须有前置数据三个题目单选题题型x，多选题型y，判断题型z，分别对应前台回显。如果没有添加试题时题型无法回显，无法选择题型添加题目；登录系统然后通过试题管理添加试题表单进行添加试题，再组成试卷，发布考试，前台即可考试，后台生成记录。所有数据都有了。


        目前系统已经完整上传，系统能够完整运行。已经有好几个同学咨询作为毕业设计了，希望对更多人有所帮助。
        有问题请联系舒照：13409779190（同微信）

