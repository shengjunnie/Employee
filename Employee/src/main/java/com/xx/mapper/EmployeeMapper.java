package com.xx.mapper;


import com.xx.entity.Employee;
import com.xx.entity.UncheckedWork;

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
	
	
	/**
	 * 员工提交工作记录审查
	 * @param work 封装了员工id 工作产品 工作方式 数量 工作方式的id
	 * @return
	 */
	Integer addUncheckedWork(UncheckedWork work);
	
	
	/**
	 * 员工提交工作记录审查前  判断今日是否已在审核表内 01
	 * @param work 封装了员工id
	 * @return
	 */
	Integer ExistChecked(UncheckedWork work);
	
	
	/**
	 * 员工提交工作记录审查前 判断今日是否已在未审核表内
	 * @param work 封装了员工id
	 * @return
	 */
	Integer ExistUnchecked(UncheckedWork work);
	
	
	/**
	 * 提交前判断是否今日提交过  若已提交 先删除 再提交
	 * @param work 封装了员工id
	 * @return
	 */
	Integer deleteExistUnchecked(UncheckedWork work);
}
