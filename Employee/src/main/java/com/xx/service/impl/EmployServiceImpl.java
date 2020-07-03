package com.xx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xx.entity.Employee;
import com.xx.mapper.EmployeeMapper;
import com.xx.service.IEmployeeService;

@Service
public class EmployServiceImpl implements IEmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	
	/**
	 * 员工登录
	 * @param employee 封装了员工姓名以及员工密码
	 * @return 返回employee
	 */
	public Employee employeeLogin(Employee employee) {
		
		Integer returnStatus = 1;
		// 判断名字是否为空 10  密码是否为空 9  用户名是否存在 8  密码是否正确 7 登录成功1
		if (employee.getEmployeeName().isEmpty()) {
			returnStatus = 10;
		} else if (employee.getEmployeePassword().isEmpty()) {
			returnStatus = 9;
		} else {
			if(employeeMapper.beforeLogin1(employee) == 0) {
				returnStatus = 8;
			}else {
				if(employeeMapper.beforeLogin2(employee) == 0) {
					returnStatus = 7;
				}
			}
			
		}
		
		Employee user = new Employee();
		if(returnStatus == 1) {
			user = employeeMapper.employeeLogin(employee);
		}
		user.setReturnStatus(returnStatus);
		return user;
		
	}
	
	
	
}
