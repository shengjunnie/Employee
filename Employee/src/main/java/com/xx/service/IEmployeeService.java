package com.xx.service;

import com.xx.entity.Employee;

public interface IEmployeeService {

	/**
	 * 员工登录
	 * @param employee 封装了员工姓名以及员工密码
	 * @return 返回employee
	 */
	public Employee employeeLogin(Employee employee);
	

}
