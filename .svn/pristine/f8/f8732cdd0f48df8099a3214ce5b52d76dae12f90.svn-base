package org.mdacc.rists.cghub.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.mdacc.rists.cghub.dao.CghubDao;
import org.mdacc.rists.cghub.model.GroupTb;
import org.mdacc.rists.cghub.model.PairTb;
import org.mdacc.rists.cghub.model.SeqTb;


public class TestApp {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CghubDB");
		CghubDao cghubDao = new CghubDao(factory.createEntityManager());
		cghubDao.entityManager.getTransaction().begin();
		Date dt = new Date();
		GroupTb group = new GroupTb("golden");
		PairTb pair = new PairTb("pairID");
		List<SeqTb> seqList = new ArrayList<SeqTb>();
		List<PairTb> pairList = new ArrayList<PairTb>();
		SeqTb seq1 = new SeqTb("aliquot", "101", "mda", "checksum", "url", "filename", "filepath",100, dt,"participant", "platform", dt,
				"refassem", "sampleid", "format", "tcga", "study",  "tissue");
		seq1.setPairTb(pair);
		seqList.add(seq1);
		SeqTb seq2 = new SeqTb("aliquot", "102", "mda", "checksum", "url", "filename", "filepath",100, dt,"participant", "platform", dt,
				"refassem", "sampleid", "format", "tcga", "study",  "tissue");
		seq2.setPairTb(pair);
		seqList.add(seq2);
		pair.setSeqTbs(seqList);
		pair.setGroupTb(group);
		pairList.add(pair);
		group.setPairTbs(pairList);
		cghubDao.entityManager.persist(group);
		cghubDao.entityManager.getTransaction().commit();
		
	}
}