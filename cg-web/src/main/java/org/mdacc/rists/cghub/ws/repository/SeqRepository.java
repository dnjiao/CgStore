package org.mdacc.rists.cghub.ws.repository;

import java.util.List;

import org.mdacc.rists.cghub.model.SeqTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeqRepository extends JpaRepository<SeqTb, Integer> {

	@Override
	public List<SeqTb> findAll();
//	@Query(value = "SELECT analysisId, filename FROM SeqTb s where s.analysisId = :analysisId", nativeQuery=true)
	public SeqTb findByAnalysisId(String analysisId);
}
