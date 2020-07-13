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
import com.xx.entity.UncheckedWork;
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
	
	
	/**
	 * 员工添加做工信息 进入待审核表
	 * 10意味着已经填写并审核成功，如需更改请联系管理员
	 * 9 已经提交过，是否重新提交
	 * 8 信息未填写完整
	 * 7 未得到员工信息
	 * 2 添加失败
	 * 1 添加成功
	 */
	@RequestMapping("/addwork.action")
	public void addUncheckedWork(HttpServletRequest request,
			HttpServletResponse response,UncheckedWork work) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		System.out.println("*****员工添加做工信息*****");
		if(employeeServiceImpl.ExistChecked(work) == 1) {
			response.getWriter().print(10);
			return;   
		}
		if(employeeServiceImpl.ExistUnchecked(work) == 1) {
			response.getWriter().print(9);
			return;
		}
		response.getWriter().print(employeeServiceImpl.addUncheckedWork(work));
		
		System.out.println("*****员工添加做工信息*****");
	}
	
	
	/**
	 * 员工添加做工信息 若员工今日 已添加 覆盖再添加
	 * 8 信息未填写完整
	 * 7 未得到员工信息
	 * 6 覆盖前删除失败
	 * 2 添加失败
	 * 1 添加成功
	 */
	@RequestMapping("/addwork02.action")
	public void addUncheckedWork02(HttpServletRequest request,
			HttpServletResponse response,UncheckedWork work) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		System.out.println("*****员工添加做工信息 覆盖再添加*****");

		if(employeeServiceImpl.deleteExistUnchecked(work) == 0) {
			response.getWriter().print(6);
			return;
		}
		response.getWriter().print(employeeServiceImpl.addUncheckedWork(work));
		
		System.out.println("*****员工添加做工信息 覆盖再添加*****");
	}
}
