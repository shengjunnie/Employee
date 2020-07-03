package com.xx.mapper;

import com.xx.entity.Employee;

public interface EmployeeMapper {
	
	/**
	 * 员工登录
	 * @param employee 封装了员工姓名以及员工密码
	 * @return 返回employee
	 */
	Employee employeeLogin(Employee employee);
	
	
	/**
	 * 员工登录前查找是否有该用户名
	 * @param employee 封装了员工姓名
	 * @return integer
	 */
	Integer beforeLogin1(Employee employee);
	
	
	/**
	 * 员工登录前 判断密码是否正确
	 * @param employee 封装了员工姓名以及员工密码
	 * @return integer
	 */
	Integer beforeLogin2(Employee employee);
}
