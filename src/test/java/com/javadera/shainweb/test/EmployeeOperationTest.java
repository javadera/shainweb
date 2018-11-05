package com.javadera.shainweb.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.javadera.shainweb.model.Employee;
import com.javadera.shainweb.model.RoleMaster;
import com.javadera.shainweb.service.AbstractOperation;
import com.javadera.shainweb.service.EmployeeOperation;
import com.javadera.shainweb.service.RoleMasterOperation;
import com.javadera.shainweb.util.Resources;


@RunWith(Arquillian.class)
public class EmployeeOperationTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(Employee.class, RoleMaster.class, AbstractOperation.class, EmployeeOperation.class, RoleMasterOperation.class, Resources.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource("test-ds.xml");
    }

    @Inject
    EmployeeOperation employeeOperation;

    @Inject
    RoleMasterOperation roleMasterOperation;

    @Inject
    Logger log;

    @Before
    public void testBefore() {
      prepareRoleMaster();
    }

    @After
    public void testAfter() {
    }


    @Test
    public void addEmployee() throws Exception {
      Employee test = getTestEmployee();
      employeeOperation.addEmployee(test);
      test = employeeOperation.getEmployeeById("AC160099");
      assertEquals(test.getShainId(), "AC160099");
    }

    @Test
    public void removeEmployee() throws Exception {
//      Employee test = getTestEmployee();
//      test.setShainId("AF160100");
//      employeeOperation.addEmployee(test);
//      employeeOperation.removeEmployee(test);
//      test = employeeOperation.getEmployeeById("AF160100");
//      assertNull(test);
    }

    @Test
    public void updateEmployee() {
      Employee test = getTestEmployee();
      test.setShainId("AF160199");
      test.setEmail("test@shainweb.com");
      employeeOperation.addEmployee(test);
      // email の更新
      test.setEmail("testupdate@shainweb.com");
      employeeOperation.updateEmployee(test);
      test = employeeOperation.getEmployeeById("AF160199");
      assertEquals(test.getEmail(), "testupdate@shainweb.com");
      employeeOperation.removeEmployee(test);

    }

    /**
     * ロールマスタデータを挿入します。
     */
    private void prepareRoleMaster() {
      List<RoleMaster> list = roleMasterOperation.getAll();
      if (list.size() == 0) {
          RoleMaster rm = new RoleMaster();
          rm.setRoleId(1);
          rm.setRoleName("USER");
          roleMasterOperation.addRoleMaster(rm);
          rm.setRoleId(2);
          rm.setRoleName("ADMIN");
          roleMasterOperation.addRoleMaster(rm);
      }
    }

    /**
     * ロールマスタデータを削除します。
     */
    private void destroyRoleMaster() {
      RoleMaster rm = new RoleMaster();
      rm.setRoleId(1);
      rm.setRoleName("USER");
      roleMasterOperation.removeRoleMaster(rm);
      rm.setRoleId(2);
      rm.setRoleName("ADMIN");
      roleMasterOperation.removeRoleMaster(rm);
      employeeOperation.removeEmployee(getTestEmployee());
    }

    /**
     * テスト用データを取得します。
     * @return
     */
    private Employee getTestEmployee() {
      Employee test = new Employee();
      test.setAddress("試験住所");
      test.setCellphoneNumber("09099998888");
      test.setDeleteFlag(0);
      test.setEmail("shiken0@shainweb.com");
      test.setFamilyName("姓");
      test.setFamilyNameKana("セイ");
      test.setGivenName("名");
      test.setGivenNameKana("メイ");
      test.setJoinDate(new Date());
      test.setPassword("test");
      test.setPhoneNumber("0312341234");
      test.setQuitDate(new Date());
      test.setShainId("AC160099");
      RoleMaster rm = new RoleMaster();
      rm.setRoleId(1);
      rm.setRoleName("USER");
      test.setRoleMaster(rm);
      return test;
    }
}
