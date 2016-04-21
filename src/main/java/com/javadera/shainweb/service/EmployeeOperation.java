package com.javadera.shainweb.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import com.javadera.shainweb.model.Employee;
import com.javadera.shainweb.model.Employee_;
import com.javadera.shainweb.model.RoleMaster;


/**
 * Employee テーブルの操作を行うクラス
 *
 */
@Stateless
public class EmployeeOperation extends AbstractOperation {

    /**
     * Employee を挿入します。
     * @param emp
     */
    public void addEmployee(Employee emp) {
        em.persist(emp);
    }

    /**
     * Employee を削除します。
     * @param emp
     */
    public void removeEmployee(Employee emp) {
        Employee target = em.merge(emp);
        em.remove(target);
    }

    /**
     * Employee を更新します。
     * @param emp
     * @return
     */
    public Employee updateEmployee(Employee emp) {
        Employee target = em.merge(emp);
        em.flush();
        return target;
    }

    /**
     * ID を指定して Employee を取得します。
     * @param id
     * @return
     */
    public Employee getEmployeeById(String shainId) {
        Query q = em.createNamedQuery("Employee.findById");
        q.setParameter("shainId", shainId);
        return (Employee)q.getSingleResult();
    }

   /**
    * ID を指定して Employee を取得します。
    * (Criteria Query を用いた手法)
    * @param id
    * @return
    */
   public Employee getEmployeeByIdCriteria(String shainId) {
       CriteriaBuilder cb = em.getCriteriaBuilder();
       CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
       Root<Employee> empRoot = criteria.from(Employee.class);
       criteria.select(empRoot).where(cb.equal(empRoot.get(Employee_.shainId), shainId));
       return em.createQuery(criteria).getSingleResult();
   }

    /**
     * ID 順に Employee 一覧を取得します。
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Employee> getEmployeeList() {
        Query q = em.createNamedQuery("Employee.findAllOrderById");
        return q.getResultList();
    }

    /**
     * ID 順に Employee 一覧を取得します。
     * (Criteria Query を用いた手法)
     * @return
     */
    public List<Employee> getEmployeeListCriteria() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> emp = criteria.from(Employee.class);
        criteria.select(emp).orderBy(cb.asc(emp.get(Employee_.shainId)));
        return em.createQuery(criteria).getResultList();
    }

    /**
     * パスワード以外の情報を更新します。
     * @param shainId
     * @param roleMaster
     * @param familyName
     * @param familyNameKana
     * @param givenName
     * @param givenNameKana
     * @param email
     * @param phoneNumber
     * @param cellphoneNumber
     * @param address
     * @param joinDate
     * @param quitDate
     * @param deleteFlag
     */
    public void updateEmployee(String shainId,
					    		RoleMaster roleMaster,
					    		String familyName,
					    		String familyNameKana,
					    		String givenName,
					    		String givenNameKana,
					    		String email,
					    		String phoneNumber,
					    		String cellphoneNumber,
					    		String address,
					    		Date joinDate,
					    		Date quitDate,
					    		Integer deleteFlag)
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Employee> update = cb.createCriteriaUpdate(Employee.class);
        Root<Employee> emp = update.from(Employee.class);
        update.set(Employee_.roleMaster, roleMaster);
        update.set(Employee_.familyName, familyName);
        update.set(Employee_.familyNameKana, familyNameKana);
        update.set(Employee_.givenName, givenName);
        update.set(Employee_.givenNameKana, givenNameKana);
        update.set(Employee_.email, email);
        update.set(Employee_.phoneNumber, phoneNumber);
        update.set(Employee_.cellphoneNumber, cellphoneNumber);
        update.set(Employee_.address, address);
        update.set(Employee_.joinDate, joinDate);
        update.set(Employee_.quitDate, quitDate);
        update.set(Employee_.deleteFlag, deleteFlag);
        update.where(cb.equal(emp.get(Employee_.shainId), shainId));
        em.createQuery(update).executeUpdate();
    }

    /**
     * パスワードの更新
     * @param shainId
     * @param password
     */
    public void updatePassword(String shainId, String password) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Employee> update = cb.createCriteriaUpdate(Employee.class);
        Root<Employee> emp = update.from(Employee.class);
        update.set(Employee_.password, password);
        update.where(cb.equal(emp.get(Employee_.shainId), shainId));
        em.createQuery(update).executeUpdate();
    }
}