package com.javadera.shainweb.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * データ操作を行うための基底クラス
 *
 */
public abstract class AbstractOperation {
   @PersistenceContext(unitName="primary")
   protected EntityManager em;
}