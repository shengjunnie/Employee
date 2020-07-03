package com.xx.service;

import com.xx.entity.AdminInfo;
import com.xx.entity.Employee;

public interface IAdminService {
	
	
	/**
	 * 管理人员登录
	 * @param adminInfo 需要管理人员的 姓名 和 密码
	 * @return 返回管理人员信息
	 */
	public AdminInfo adminLogin(AdminInfo adminInfo);
	
	
	/**
	 * 管理人员 添加新员工
	 * @param employee 需要新员工的姓名 及身份证
	 * @return 返回integer
	 */
	public Integer insertEmployee(Employee employee);
	
	
	/**
	 * 管理人员 编辑修改员工信息
	 * @param employee 被修改员工的id 姓名 身份证 密码
	 * @return
	 */
	public Integer updateEmployee(Employee employee);
}
