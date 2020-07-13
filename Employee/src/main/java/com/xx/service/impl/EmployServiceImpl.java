package com.xx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xx.entity.Employee;
import com.xx.entity.UncheckedWork;
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
		if (employee.getEmployeeName() == null) {
			returnStatus = 10;
		} else if (employee.getEmployeePassword() == null) {
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
	
	
	/**
	 * 员工提交工作记录审查前  判断今日是否已在审核表内 01
	 * @param work 封装了员工id
	 * @return
	 */
	public Integer ExistChecked(UncheckedWork work) {
		return employeeMapper.ExistChecked(work);
	}
	
	
	/**
	 * 员工提交工作记录审查前 判断今日是否已在未审核表内
	 * @param work 封装了员工id
	 * @return
	 */
	public Integer ExistUnchecked(UncheckedWork work) {
		return employeeMapper.ExistUnchecked(work);
	}
	
	
	/**
	 * 员工提交工作记录审查
	 * @param work 封装了员工id 工作产品 工作方式 数量 工作方式的id
	 * @return
	 */
	public Integer addUncheckedWork(UncheckedWork work) {
		if(work.getCargo() == null || work.getWorkType() == null || work.getQuantity() == 0) {
			return 8;
		}
		if(work.getEmployeeId() == null || work.getEmployeeName() == null) {
			return 7;
		}
		if(employeeMapper.addUncheckedWork(work) == 1) {
			return 1;
		}else {
			return 2;
		}
	}
	
	
	/**
	 * 提交前判断是否今日提交过  若已提交 先删除 再提交
	 * @param work 封装了员工id
	 * @return
	 */
	public Integer deleteExistUnchecked(UncheckedWork work) {
		return employeeMapper.deleteExistUnchecked(work);
	}
}
