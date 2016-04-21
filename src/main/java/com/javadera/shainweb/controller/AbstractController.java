package com.javadera.shainweb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.javadera.shainweb.model.Employee;
import com.javadera.shainweb.model.RoleMaster;
import com.javadera.shainweb.service.EmployeeOperation;
import com.javadera.shainweb.service.RoleMasterOperation;


/**
 * コントローラの基底クラス
 * @author
 *
 */
public abstract class AbstractController {
    @Inject protected EmployeeOperation empOpe;
    @Inject protected RoleMasterOperation roleOpe;
    @Inject protected Logger log;

    protected Employee emp = new Employee();
    protected List<RoleMaster> roleList = new ArrayList<>();
    protected List<Employee> empList = new ArrayList<>();

    public void init() {
    	roleList= roleOpe.getAll();
        empList = empOpe.getEmployeeList();
    }

    /**
     * PSQLException メッセージを取得します。
     * (DB が替わると使えない。もっとスマートな方法がある？)
     * @param caused
     * @return
     */
    protected String getPSQLExceptionLocalizedMessage(Throwable caused) {
    	Throwable th = caused.getCause();
    	if (th.toString().contains("PSQLException")) {
    		return th.getLocalizedMessage();
    	}
    	else {
    		return getPSQLExceptionLocalizedMessage(th);
    	}
    }

    //--- getter, setter ---
    public List<Employee> getEmpList() {
        return empList;
    }

    public void setEmpList(List<Employee> empList) {
        this.empList = empList;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

	public List<RoleMaster> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleMaster> roleList) {
		this.roleList = roleList;
	}

}
