package com.javadera.shainweb.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-02-01T10:46:15.660+0900")
@StaticMetamodel(RoleMaster.class)
public class RoleMaster_ {
	public static volatile SingularAttribute<RoleMaster, Integer> roleId;
	public static volatile SingularAttribute<RoleMaster, String> roleName;
	public static volatile SetAttribute<RoleMaster, Employee> employees;
}
