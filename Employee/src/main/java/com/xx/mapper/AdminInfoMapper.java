package com.xx.mapper;

import com.xx.entity.AdminInfo;
import com.xx.entity.Employee;

public interface AdminInfoMapper {
	
	
	/**
	 * 管理人员登录
	 * @param adminInfo 需要管理人员的 姓名 和 密码
	 * @return 返回管理人员信息
	 */
	AdminInfo adminLogin(AdminInfo adminInfo);
	
	
	/**
	 * 管理人员登录前 判断管理人员用户名是否正确
	 * @param adminInfo 需要管理人员的姓名
	 * @return 返回integer
	 */
	Integer beforeAdminLogin1(AdminInfo adminInfo);
	
	
	/**
	 * 管理人员登录前 判断管理人员用户名密码是否正确
	 * @param adminInfo 需要管理人员的姓名
	 * @return 返回integer
	 */
	Integer beforeAdminLogin2(AdminInfo adminInfo);
	
	
	/**
	 * 管理人员 添加新员工
	 * @param employee 需要新员工的姓名 及身份证
	 * @return 返回integer
	 */
	Integer insertEmployee(Employee employee);
	
	
	/**
	 * 管理人员 编辑修改员工信息
	 * @param employee 被修改员工的id 姓名 身份证 密码
	 * @return
	 */
	Integer updateEmployee(Employee employee);
	
}
