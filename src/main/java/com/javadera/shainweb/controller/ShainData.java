package com.javadera.shainweb.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.javadera.shainweb.model.Employee;
import com.javadera.shainweb.model.RoleMaster;
import com.javadera.shainweb.util.SHA256Encoder;


@Named
@SessionScoped
public class ShainData extends AbstractController implements Serializable {

  private static final long serialVersionUID = 1L;
    private String roleString = "";
    private boolean newStaff = false;
  private String buttonCaption = "";

  @PostConstruct
  public void init() {
    super.init();
  }

    /**
     * 新規追加画面に遷移します。
     * @return
     */
    public String addEmp() {
      this.emp = new Employee();
      newStaff = true;
      roleString = "USER";
      buttonCaption = "追加";
        return "shainData.xhtml";
    }

    /**
     * 社員情報編集画面に遷移します。
     * @param emp
     * @return
     */
    public String editEmp(Employee emp) {
      this.emp = emp;
      newStaff = false;
      roleString = this.emp.getRoleMaster().getRoleName();
      buttonCaption = "更新";
        return "shainData.xhtml";
    }

    /**
     * 社員情報を追加/更新します。
     * @return
     */
    public String executeEmp() {
        RoleMaster role = roleOpe.getByName(roleString);
        emp.setRoleMaster(role);
        emp.setDeleteFlag(0);
        try {
            if (newStaff) {
                // 初回登録時は固定のパスワードを設定しておく
                emp.setPassword(SHA256Encoder.getBase64EncodedPassword("password#!"));
                empOpe.addEmployee(emp);
            }
            else {
                empOpe.updateEmployee(emp.getShainId(),
                    emp.getRoleMaster(),
                    emp.getFamilyName(),emp.getFamilyNameKana(),
                    emp.getGivenName(), emp.getGivenNameKana(),
                    emp.getEmail(),
                    emp.getPhoneNumber(), emp.getCellphoneNumber(),
                    emp.getAddress(),
                    emp.getJoinDate(),emp.getQuitDate(),
                    emp.getDeleteFlag());
            }
        }
        catch (Exception ex) {
          String errorMessage = getPSQLExceptionLocalizedMessage(ex.getCause());
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "エラー", errorMessage));
          return "";
        }
        return "shainList.xhtml";
    }

  //--- getter, setter ---
  public String getButtonCaption() {
    return buttonCaption;
  }

  public void setButtonCaption(String buttonCaption) {
    this.buttonCaption = buttonCaption;
  }

  public String getRoleString() {
    return roleString;
  }

  public void setRoleString(String roleString) {
    this.roleString = roleString;
  }

  public boolean isNewStaff() {
    return newStaff;
  }

  public void setNewStaff(boolean newStaff) {
    this.newStaff = newStaff;
  }

}
