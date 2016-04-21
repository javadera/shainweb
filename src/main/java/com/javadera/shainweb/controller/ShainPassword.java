package com.javadera.shainweb.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.javadera.shainweb.model.Employee;
import com.javadera.shainweb.util.SHA256Encoder;


@Named
@SessionScoped
public class ShainPassword extends AbstractController implements Serializable {

	private static final long serialVersionUID = 1L;
	private String newPassword = "";

	@PostConstruct
	public void init() {
		super.init();
	}

	/**
	 * パスワード更新画面を表示します。
	 * @param emp
	 * @return
	 */
	public String changePassword(Employee emp) {
		this.emp = emp;
		return "shainPassword.xhtml";
	}

	/**
	 * パスワードを更新します。
	 * @return
	 */
	public String updatePassword() {
		String encodedPassword = SHA256Encoder.getBase64EncodedPassword(newPassword);
		empOpe.updatePassword(emp.getShainId(), encodedPassword);
		return "shainList.xhtml";
	}

	//--- getter, setter ---
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
