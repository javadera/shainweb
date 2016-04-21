package com.javadera.shainweb.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-02-01T10:46:15.529+0900")
@StaticMetamodel(Employee.class)
public class Employee_ {
	public static volatile SingularAttribute<Employee, String> shainId;
	public static volatile SingularAttribute<Employee, RoleMaster> roleMaster;
	public static volatile SingularAttribute<Employee, String> familyName;
	public static volatile SingularAttribute<Employee, String> familyNameKana;
	public static volatile SingularAttribute<Employee, String> givenName;
	public static volatile SingularAttribute<Employee, String> givenNameKana;
	public static volatile SingularAttribute<Employee, String> email;
	public static volatile SingularAttribute<Employee, String> phoneNumber;
	public static volatile SingularAttribute<Employee, String> cellphoneNumber;
	public static volatile SingularAttribute<Employee, String> address;
	public static volatile SingularAttribute<Employee, Date> joinDate;
	public static volatile SingularAttribute<Employee, Date> quitDate;
	public static volatile SingularAttribute<Employee, String> password;
	public static volatile SingularAttribute<Employee, Integer> deleteFlag;
}
