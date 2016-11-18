package com.cn.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TestInterceptor implements HandlerInterceptor{

    /**
     * ��Դ���ٵȲ���
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        
        System.out.println("afterCompletion.........");
        
    }

    /**
     * ����ͨ��ModelAndView arg3 �����ı���ʵ����ͼ ���޸ķ�����ͼ�ķ���
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        
        arg3.addObject("arg", "���Ǵ������������Ĳ���");
        arg3.setViewName("success");
        System.out.println("postHandle.........");
        
    }

    /**
     * ����true:���󽫼���ִ��
     * ����false:���󽫱���ֹ
     *  Object arg2 ��ʾ�����ص������Ŀ�����
     */
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {

        System.out.println("preHandle.........");
        return true;
    }

}
