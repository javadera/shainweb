package com.javadera.shainweb.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.javadera.shainweb.model.RoleMaster;


@Stateless
public class RoleMasterOperation extends AbstractOperation {

	private final String USER = "USER";
	private final String ADMIN = "ADMIN";

	/**
	 * RoleMaster を挿入します。
	 * @param roleMaster
	 */
	public void addRoleMaster(RoleMaster roleMaster) {
		em.persist(roleMaster);
	}

	/**
	 * RoleMaster を削除します。
	 * @param roleMaster
	 */
	public void removeRoleMaster(RoleMaster roleMaster) {
		RoleMaster target = em.merge(roleMaster);
		em.remove(target);
	}

	/**
	 * RoleMaster を更新します。
	 * @param roleMaster
	 * @return
	 */
	public RoleMaster updateRoleMaster(RoleMaster roleMaster) {
		RoleMaster target = em.merge(roleMaster);
		em.flush();
		return target;
	}

	/**
	 * RoleMaster 一覧を ID 順に取得する
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RoleMaster> getAll() {
        Query q = em.createNamedQuery("RoleMaster.findAllOrderById");
        return (List<RoleMaster>)q.getResultList();
	}

	/**
	 * RoleMaster を Role 名を指定して取得する
	 * @param name
	 * @return
	 */
	public RoleMaster getByName(String name) {
        Query q = em.createNamedQuery("RoleMaster.findByName");
        q.setParameter("roleName", name);
        return (RoleMaster)q.getSingleResult();
	}

	/**
	 * USER ロールのレコードを取得します。
	 * @return
	 */
	public RoleMaster getUserRole() {
		return getByName(USER);
	}

	/**
	 * Admin ロールのレコードを取得します。
	 * @return
	 */
	public RoleMaster getAdminRole() {
		return getByName(ADMIN);
	}
}
