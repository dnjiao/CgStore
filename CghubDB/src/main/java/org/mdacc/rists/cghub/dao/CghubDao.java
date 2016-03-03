package org.mdacc.rists.cghub.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.mdacc.rists.cghub.model.GroupTb;
import org.mdacc.rists.cghub.model.PairTb;
import org.mdacc.rists.cghub.model.SeqTb;

public class CghubDao {
	
	static EntityManager entityManager;
	
	public CghubDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	

	public static void insertSeq(SeqTb seq) {
		EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(seq);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
	}
	
	public static void insertPair(PairTb pair) {
		EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(pair);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
	}
	
	public static void insertGroup(GroupTb group) {
		EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(group);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
	}
	
}
