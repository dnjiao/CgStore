package org.mdacc.rists.cghub.ws.service;

import java.util.List;

import org.mdacc.rists.cghub.model.SeqTb;

public interface SeqService {
	List<SeqTb> findAll();
	SeqTb findByAnalysisId(String analysisId);
}
