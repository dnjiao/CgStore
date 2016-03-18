package org.mdacc.rists.cghub.dao;

import java.io.Serializable;
import java.util.Date;

public class SeqTbVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String aliquotId;
	private String analysisId;
	private String analyteCode;
	private String center;
	private String checksum;
	private String dataUrl;
	private String dccProject;
	private String dccSpecimenType;
	private String diseaseAbbr;
	private String diseaseFull;
	private String filename;
	private String filepath;
	private int filesize;
	private Date lastModified;
	private String legacyId;
	private String participantId;
	private String platform;
	private Date publishedDate;
	private String refassem;
	private String sampleId;
	private String sampleType;
	private String library;
	private String seqFormat;
	private String seqSource;
	private String specimenId;
	private String study;
	private String tissueType;
	private String tssId;
	private String pairId;
	private String groupId;
	
	public SeqTbVo() {
		super();
	}

	public String getAliquotId() {
		return aliquotId;
	}

	public void setAliquotId(String aliquotId) {
		this.aliquotId = aliquotId;
	}

	public String getAnalysisId() {
		return analysisId;
	}

	public void setAnalysisId(String analysisId) {
		this.analysisId = analysisId;
	}

	public String getAnalyteCode() {
		return analyteCode;
	}

	public void setAnalyteCode(String analyteCode) {
		this.analyteCode = analyteCode;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getDataUrl() {
		return dataUrl;
	}

	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

	public String getDccProject() {
		return dccProject;
	}

	public void setDccProject(String dccProject) {
		this.dccProject = dccProject;
	}

	public String getDccSpecimenType() {
		return dccSpecimenType;
	}

	public void setDccSpecimenType(String dccSpecimenType) {
		this.dccSpecimenType = dccSpecimenType;
	}

	public String getDiseaseAbbr() {
		return diseaseAbbr;
	}

	public void setDiseaseAbbr(String diseaseAbbr) {
		this.diseaseAbbr = diseaseAbbr;
	}

	public String getDiseaseFull() {
		return diseaseFull;
	}

	public void setDiseaseFull(String diseaseFull) {
		this.diseaseFull = diseaseFull;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public int getFilesize() {
		return filesize;
	}

	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getLegacyId() {
		return legacyId;
	}

	public void setLegacyId(String legacyId) {
		this.legacyId = legacyId;
	}

	public String getParticipantId() {
		return participantId;
	}

	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getRefassem() {
		return refassem;
	}

	public void setRefassem(String refassem) {
		this.refassem = refassem;
	}

	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public String getSampleType() {
		return sampleType;
	}

	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}

	public String getLibrary() {
		return library;
	}

	public void setLibrary(String library) {
		this.library = library;
	}

	public String getSeqFormat() {
		return seqFormat;
	}

	public void setSeqFormat(String seqFormat) {
		this.seqFormat = seqFormat;
	}

	public String getSeqSource() {
		return seqSource;
	}

	public void setSeqSource(String seqSource) {
		this.seqSource = seqSource;
	}

	public String getSpecimenId() {
		return specimenId;
	}

	public void setSpecimenId(String specimenId) {
		this.specimenId = specimenId;
	}

	public String getStudy() {
		return study;
	}

	public void setStudy(String study) {
		this.study = study;
	}

	public String getTissueType() {
		return tissueType;
	}

	public void setTissueType(String tissueType) {
		this.tissueType = tissueType;
	}

	public String getTssId() {
		return tssId;
	}

	public void setTssId(String tssId) {
		this.tssId = tssId;
	}

	public String getPairId() {
		return pairId;
	}

	public void setPairId(String pairId) {
		this.pairId = pairId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}	
	
}
