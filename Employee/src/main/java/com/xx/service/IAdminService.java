package com.xx.service;

import java.util.List;

import com.xx.entity.AdminInfo;
import com.xx.entity.Employee;
import com.xx.entity.EmployeeWork;
import com.xx.entity.UncheckedWork;

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
	
	
	/**
	 * 管理员查看所有未审核
	 * @return
	 */
	public List<UncheckedWork> selectAllUnchecked();
	
	
	/**
	 * 管理员审查通过
	 * @param work
	 * @return
	 */
	public Integer uncheckedPass(EmployeeWork work);
	
	
	/**
	 * 管理员审查不通过  删除待审核表做工情况
	 * @param employeeId 员工的id
	 * @return
	 */
	public Integer deleteUnchecked(EmployeeWork work);
}
