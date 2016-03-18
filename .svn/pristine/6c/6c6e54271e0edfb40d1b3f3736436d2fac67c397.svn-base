package org.mdacc.rists.cghub.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.mdacc.rists.cghub.model.GroupTb;
import org.mdacc.rists.cghub.model.PairTb;
import org.mdacc.rists.cghub.model.SeqTb;

public class CghubDao {
	
	EntityManager entityManager;
	
	public CghubDao() {		
	}	

	public CghubDao(EntityManager manager) {
		this.entityManager = manager;
	}

	public void insertSeq(SeqTb seq) {
		EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(seq);
            transaction.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            transaction.rollback();
        }
	}
	
	public void insertPair(PairTb pair) {
		EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(pair);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
	}
	
	public void insertGroup(GroupTb group) {
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
