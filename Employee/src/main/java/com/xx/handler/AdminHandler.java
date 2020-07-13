package com.xx.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xx.entity.AdminInfo;
import com.xx.entity.Employee;
import com.xx.entity.EmployeeWork;
import com.xx.entity.UncheckedWork;
import com.xx.service.IAdminService;

@Controller
@RequestMapping("/admin")
public class AdminHandler {
	
	@Autowired
	private IAdminService adminServiceImpl;
	
	/**
	 * 管理员登录
	 */
	@RequestMapping("/login.action")
	public void employeeLogin(HttpServletRequest request,
			HttpServletResponse response,AdminInfo adminInfo) throws IOException{
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("*****管理员登录*****");
		
		AdminInfo admin = adminServiceImpl.adminLogin(adminInfo);
		String json =JSON.toJSONString(admin,SerializerFeature.WriteMapNullValue);
		response.getWriter().print(json);
		
		System.out.println("*****管理员登录*****");
	}
	
	
	/**
	 * 管理员添加新员工
	 */
	@RequestMapping("/insertemployee.action")
	public void insertEmployee(HttpServletRequest request,
			HttpServletResponse response,Employee employee) throws IOException{
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("*****管理员管理员添加新员工*****");
		System.out.println(employee.getEmployeeName());
		System.out.println(employee.getIdCard());
		System.out.println(employee.getPhoneNum());
		response.getWriter().print(adminServiceImpl.insertEmployee(employee));
		
		System.out.println("*****管理员添加新员工*****");
	}
	
	
	/**
	 * 管理员 编辑修改员工信息
	 */
	@RequestMapping("/updateemployee.action")
	public void updateEmployee(HttpServletRequest request,
			HttpServletResponse response,Employee employee) throws IOException{
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("*****管理员 编辑修改员工信息*****");
		
		response.getWriter().print(adminServiceImpl.updateEmployee(employee));
		
		System.out.println("*****管理员 编辑修改员工信息*****");
	}
	
	
	/**
	 * 管理员 查看所有未审核
	 */
	@RequestMapping("/updateemployee.action")
	public void selectAllUnchecked(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("*****管理员 查看所有未审核*****");
		List<UncheckedWork> listUnchecked = adminServiceImpl.selectAllUnchecked();
		String json = JSON.toJSONString(listUnchecked);
		response.getWriter().print(json);
		
		System.out.println("*****管理员 查看所有未审核*****");
	}
	
	
	/**
	 * 管理员 审核通过
	 */
	@RequestMapping("/uncheckedpass.action")
	public void uncheckedPass(HttpServletRequest request,
			HttpServletResponse response,EmployeeWork work) throws IOException{
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("*****管理员 审核通过*****");
		
		response.getWriter().print(adminServiceImpl.uncheckedPass(work));
		
		System.out.println("*****管理员 审核通过*****");
	}
	
	
	/**
	 * 管理员 审核不通过
	 */
	@RequestMapping("/uncheckedrefuse.action")
	public void uncheckedRefuse(HttpServletRequest request,
			HttpServletResponse response,EmployeeWork work) throws IOException{
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("*****管理员 审核不通过*****");
		
		response.getWriter().print(adminServiceImpl.deleteUnchecked(work));
		
		System.out.println("*****管理员 审核不通过*****");
	}
}
