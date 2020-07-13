package com.xx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xx.entity.AdminInfo;
import com.xx.entity.Employee;
import com.xx.entity.EmployeeWork;
import com.xx.entity.UncheckedWork;
import com.xx.mapper.AdminInfoMapper;
import com.xx.mapper.EmployeeMapper;
import com.xx.service.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService{
	
	@Autowired
	private AdminInfoMapper adminInfoMapper;
	@Autowired
	private EmployeeMapper employeeMapper;
	
	
	/**
	 * 管理人员登录
	 * @param adminInfo 需要管理人员的 姓名 和 密码
	 * @return 返回管理人员信息
	 */
	public AdminInfo adminLogin(AdminInfo adminInfo) {
		Integer returnStatus = 1;
		// 判断名字是否为空 10  密码是否为空 9  用户名是否存在 8  密码是否正确 7 登录成功1
		if (adminInfo.getAdminName() == null) {
			returnStatus = 10;
		} else if (adminInfo.getAdminPassword() == null) {
			returnStatus = 9;
		} else {
			if(adminInfoMapper.beforeAdminLogin1(adminInfo) == 0) {
				returnStatus = 8;
			}else {
				if(adminInfoMapper.beforeAdminLogin2(adminInfo) == 0) {
					returnStatus = 7;
				}
			}
					
		}
				
		AdminInfo admin = new AdminInfo();
		if(returnStatus == 1) {
			admin = adminInfoMapper.adminLogin(adminInfo);
		}
		admin.setReturnStatus(returnStatus);
		return admin;
	}
	
	
	/**
	 * 管理人员 添加新员工
	 * @param employee 需要新员工的姓名 及身份证
	 * @return 返回integer
	 */
	public Integer insertEmployee(Employee employee) {
		if (employee.getEmployeeName() == null) {
			return 10;
		}
			
		if (employee.getIdCard() == null) {
			return 9;
		}
		
		//判断姓名是否存在重复
		if(employeeMapper.beforeLogin1(employee) != 0) {
			return 8;
		}
		
		//设置默认密码
		String employeePassword = employee.getIdCard().substring(12);
		employee.setEmployeePassword(employeePassword);
		
		//添加成功返回1 失败返回7
		if(adminInfoMapper.insertEmployee(employee) == 1) {
			return 1;
		}else {
			return 7;
		}
	}
	
	
	/**
	 * 管理人员 编辑修改员工信息
	 * @param employee 被修改员工的id 姓名 身份证 密码
	 * @return
	 */
	public Integer updateEmployee(Employee employee) {
		
		if(employee.getEmployeeId() == null) {
			return 10;
		}
		
		if(employee.getEmployeeName() == null) {
			return 9;
		}
		
		if(employee.getIdCard() == null) {
			return 8;
		}
		
		if(employee.getEmployeePassword() == null) {
			return 7;
		}
		
		//判断姓名是否存在重复
		if(employeeMapper.beforeLogin1(employee) != 0) {
			return 6;
		}
		
		if(adminInfoMapper.updateEmployee(employee) == 0) {
			return 2;
		}
		return 1;
	}
	
	
	/**
	 * 管理员查看所有未审核
	 * @return
	 */
	public List<UncheckedWork> selectAllUnchecked() {
		return adminInfoMapper.selectAllUnchecked();
	}
	
	
	/**
	 * 管理员审查通过
	 * 10 未获取到数据
	 * 3 添加成功删除失败
	 * 2 添加失败
	 * 1 成功
	 * @param work
	 * @return
	 */
	public Integer uncheckedPass(EmployeeWork work) {
		if(work.getEmployeeId() == null || work.getEmployeeName() == null || work.getCargo() == null ) {
			return 10;
		}
		if(work.getQuantity() == 0 || work.getWorkId() == null || work.getWorkType() == null) {
			return 10;
		}
		
		//根据workId查询对应的工资计算方法
		double price = adminInfoMapper.selectWorkTypePrice(work.getWorkId());
		work.setCommission((int) (price * work.getQuantity()));
		
		//将数据添加至已审核表
		if(adminInfoMapper.insertCheckWork(work) == 1) {
			//删除待审核表数据
			if(adminInfoMapper.deleteUnchecked(work) == 1) {
				return 1;
			} else {
				return 3;
			}
		} else {
			return 2;
		}
	}
	
	
	/**
	 * 管理员审查不通过  删除待审核表做工情况
	 * @param employeeId 员工的id
	 * @return
	 */
	public Integer deleteUnchecked(EmployeeWork work) {
		return adminInfoMapper.deleteUnchecked(work) == 0 ? 2 : 1;
	}
}
