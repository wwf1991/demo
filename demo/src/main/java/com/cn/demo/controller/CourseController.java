package com.cn.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cn.demo.model.Course;
import com.cn.demo.service.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private static Logger log = LoggerFactory.getLogger(CourseController.class);
    
    @Autowired
    private CourseService courseService;
    
    // URL: courses/view?courseId=123
    @RequestMapping(value="/view", method=RequestMethod.GET)
    public String viewCourse(@RequestParam("courseId")Integer courseId, Model model){
        
        log.debug("In viewCourse, courseId={}",courseId);
        Course course = courseService.getCoursebyId(courseId);
        model.addAttribute("course", course);
        return "course_overview";
    }
    
    // URL: courses/view/123
    @RequestMapping(value="/view2/{courseId}")
    public String viewCourse2(@PathVariable("courseId")Integer courseId, Map<String,Object> map){
        
        log.debug("In viewCourse, courseId={}",courseId);
        Course course = courseService.getCoursebyId(courseId);
        map.put("course", course);
        return "course_overview";
    }
    
    // URL: courses/view?courseId=123
    @RequestMapping(value="/view3")
    public String viewCourse3(HttpServletRequest request){
        Integer courseId = Integer.parseInt(request.getParameter("courseId"));
        log.debug("In viewCourse, courseId={}",courseId);
        Course course = courseService.getCoursebyId(courseId);
        request.setAttribute("course", course);
        return "course_overview";
    }
    
    // URL: courses/courses?add
    @RequestMapping(value="/admin",method=RequestMethod.GET,params="add")
    public String courseAdd(){
        
        return "course_admin/edit";
    }
    
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public String save(Course course){
        
        log.debug("Info of Course:");
        log.debug(ReflectionToStringBuilder.toString(course));
        
        //在此进行业务操作，比如数据库持久化
        course.setCourseId(123);
        // 重定向 url地址改变
        return "redirect:view2/"+course.getCourseId();
    }
    
    @RequestMapping(value="upload",method=RequestMethod.GET)
    public String showUploadPage(){
        
        return "course_admin/file";
    }
    // 文件上传
    @RequestMapping(value="doUpload", method=RequestMethod.POST)
    public String doUpload(@RequestParam(value="file") MultipartFile file) throws IOException{
        if(!file.isEmpty()){
            log.debug("ProcessFile: {}",file.getOriginalFilename());
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File("/home/wangwf/file/",System.currentTimeMillis()+file.getOriginalFilename()));
        }
        return "success";
    }
    
    // 多文件上传
    @RequestMapping(value="/doUpload2", method=RequestMethod.POST)
    public String doUploadFile2(MultipartHttpServletRequest multiRequest) throws IOException{
        Iterator<String> filesNames = multiRequest.getFileNames();
        while(filesNames.hasNext()){
            String fileName =filesNames.next();
            MultipartFile file =  multiRequest.getFile(fileName);
            if(!file.isEmpty()){
                log.debug("Process file: {}", file.getOriginalFilename());
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File("c:\\temp\\imooc\\", System.currentTimeMillis()+ file.getOriginalFilename()));
            }
        }
        return "success";
    }
    
    /**
     * 
     * @param courseId
     * @return json
     */
    @RequestMapping(value="/{courseId}",method=RequestMethod.GET)
    @ResponseBody
    public Course getCourseInJson(@PathVariable Integer courseId){
        return  courseService.getCoursebyId(courseId);
    }
    
    @RequestMapping(value="/jsontype/{courseId}",method=RequestMethod.GET)
    public  ResponseEntity<Course> getCourseInJson2(@PathVariable Integer courseId){
        Course course =   courseService.getCoursebyId(courseId);
        return new ResponseEntity<Course>(course, HttpStatus.OK);
    }
}
