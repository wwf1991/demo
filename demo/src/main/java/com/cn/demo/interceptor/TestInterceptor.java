package com.cn.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TestInterceptor implements HandlerInterceptor{

    /**
     * 资源销毁等操作
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        
        System.out.println("afterCompletion.........");
        
    }

    /**
     * 可以通过ModelAndView arg3 参数改变现实的视图 或修改发往视图的方法
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        
        arg3.addObject("arg", "这是从拦截器传来的参数");
        arg3.setViewName("success");
        System.out.println("postHandle.........");
        
    }

    /**
     * 返回true:请求将继续执行
     * 返回false:请求将被终止
     *  Object arg2 表示被拦截的请求的目标对象
     */
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {

        System.out.println("preHandle.........");
        return true;
    }

}
