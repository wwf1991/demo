package com.cn.demo.service;

import org.springframework.stereotype.Service;

import com.cn.demo.model.Course;

@Service
public interface CourseService {
	
	
	Course getCoursebyId(Integer courseId);
}
