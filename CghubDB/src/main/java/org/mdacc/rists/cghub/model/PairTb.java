package org.mdacc.rists.cghub.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the PAIR_TB database table.
 * 
 */
@Entity
@Table(name="PAIR_TB")
@NamedQuery(name="PairTb.findAll", query="SELECT p FROM PairTb p")
public class PairTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ROW_ID")
	private int rowId;

	@Column(name="PAIR_CODE")
	private String pairCode;

	//bi-directional many-to-one association to GroupTb
	@ManyToOne
	@JoinColumn(name="GROUP_ID")
	private GroupTb groupTb;

	//bi-directional many-to-one association to SeqTb
	@OneToMany(mappedBy="pairTb")
	private List<SeqTb> seqTbs;

	public PairTb() {
	}

	public PairTb(String pairCode, GroupTb groupTb) {
		super();
		this.pairCode = pairCode;
		this.groupTb = groupTb;
	}


	public int getRowId() {
		return this.rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public String getPairCode() {
		return this.pairCode;
	}

	public void setPairCode(String pairCode) {
		this.pairCode = pairCode;
	}

	public GroupTb getGroupTb() {
		return this.groupTb;
	}

	public void setGroupTb(GroupTb groupTb) {
		this.groupTb = groupTb;
	}


	public List<SeqTb> getSeqTbs() {
		return this.seqTbs;
	}

	public void setSeqTbs(List<SeqTb> seqTbs) {
		this.seqTbs = seqTbs;
	}

	public SeqTb addSeqTb(SeqTb seqTb) {
		getSeqTbs().add(seqTb);
		seqTb.setPairTb(this);

		return seqTb;
	}

	public SeqTb removeSeqTb(SeqTb seqTb) {
		getSeqTbs().remove(seqTb);
		seqTb.setPairTb(null);

		return seqTb;
	}

}