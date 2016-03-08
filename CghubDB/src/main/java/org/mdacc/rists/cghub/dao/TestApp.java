package org.mdacc.rists.cghub.dao;

import java.util.Date;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.mdacc.rists.cghub.dao.CghubDao;
import org.mdacc.rists.cghub.model.SeqTb;

public class TestApp {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CghubDB");
		CghubDao cghubDao = new CghubDao(factory.createEntityManager());
		Date dt = new Date();
		SeqTb seq = new SeqTb("aliquot", "101", "mda", "checksum", "url", "filename", "filepath",100, dt,"participant", "platform", dt,
				"refassem", "sampleid", "format", "tcga", "study",  "tissue");
		cghubDao.insertSeq(seq);				
	}
}