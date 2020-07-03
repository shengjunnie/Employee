package com.xx.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xx.entity.Employee;
import com.xx.service.IEmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeHandler {
	
	@Autowired
	private IEmployeeService employeeServiceImpl;
	
	/**
	 * 员工登录
	 */
	@RequestMapping("/login.action")
	public void employeeLogin(HttpServletRequest request,
			HttpServletResponse response,Employee employee) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		System.out.println("*****员工登录*****");
		
		System.out.println(request.getParameter("employeeName"));
		System.out.println(request.getParameter("name"));
		
		Employee user = employeeServiceImpl.employeeLogin(employee);
		System.out.println(user);
		String json =JSON.toJSONString(user,SerializerFeature.WriteMapNullValue);
		response.getWriter().print(json);
		
		System.out.println("*****员工登录*****");
	}
}
