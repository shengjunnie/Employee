package com.xx.mapper;

import java.util.List;

import com.xx.entity.AdminInfo;
import com.xx.entity.Employee;
import com.xx.entity.EmployeeWork;
import com.xx.entity.UncheckedWork;

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
	
	
	/**
	 * 管理员查看所有的未审查的做工情况
	 * @return
	 */
	List<UncheckedWork> selectAllUnchecked();
	
	
	/**
	 * 管理员审查通过计算工资时 查询工作类型的单价
	 * @param workId
	 * @return
	 */
	double selectWorkTypePrice(Integer workId);
	
	
	/**
	 * 管理员审查通过 添加到已审核表
	 * @param work 包含了员工id 姓名 货种 工作方式 数量 金额 
	 * @return
	 */
	Integer insertCheckWork(EmployeeWork work);
	
	
	/**
	 * 管理员审查不通过  删除待审核表做工情况
	 * @param employeeId 员工的id
	 * @return
	 */
	Integer deleteUnchecked(EmployeeWork work);
	
}
