package com.javadera.shainweb.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.javadera.shainweb.model.Employee;


@Named
@ViewScoped
public class ShainList extends AbstractController implements Serializable {

	private static final long serialVersionUID = 1L;

    @PostConstruct
    public void init() {
    	super.init();
    }

    /**
     * 社員情報を論理削除します。
     * @param emp
     * @return
     */
    public String deleteEmp(Employee emp) {
        this.emp = emp;
        // DeleteFlag を設定してレコード更新
        emp.setDeleteFlag(1);
        empOpe.updateEmployee(emp);
        empList = empOpe.getEmployeeList();
        return "shainList.xhtml";
    }
}
