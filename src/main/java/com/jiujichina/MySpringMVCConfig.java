package com.jiujichina;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configurable
public class MySpringMVCConfig extends WebMvcConfigurerAdapter {
	
	public void addInterceptors(InterceptorRegistry registry){
		HandlerInterceptor handlerInterceptor = new HandlerInterceptor(){

			@Override
			public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
					throws Exception {
				// TODO Auto-generated method stub
			
			}

			@Override
			public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
					throws Exception {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("自定义拦截器.............");
				return true; 
			}
			
			
		};
		registry.addInterceptor(handlerInterceptor).addPathPatterns("/**");
	}
}
