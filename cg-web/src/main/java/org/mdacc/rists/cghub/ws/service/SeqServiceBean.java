package org.mdacc.rists.cghub.ws.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.mdacc.rists.cghub.dao.CghubDao;
import org.mdacc.rists.cghub.model.SeqTb;
import org.mdacc.rists.cghub.ws.repository.SeqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class SeqServiceBean implements SeqService {
	
	@Autowired
	private SeqRepository seqRepository;
	
	@Override
	public List<SeqTb> findAll() {
		List<SeqTb> seqs = seqRepository.findAll();
		return seqs;
	}

	public SeqTb findByAnalysisId(String analysisId) {
		SeqTb seq = seqRepository.findByAnalysisId(analysisId);
		return seq;
	}

}
