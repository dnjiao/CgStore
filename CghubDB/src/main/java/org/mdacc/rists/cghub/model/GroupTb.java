package org.mdacc.rists.cghub.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the GROUP_TB database table.
 * 
 */
@Entity
@Table(name="GROUP_TB")
@NamedQuery(name="GroupTb.findAll", query="SELECT g FROM GroupTb g")
public class GroupTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ROW_ID")
	private int rowId;

	@Column(name="GROUP_NAME")
	private String groupName;

	//bi-directional many-to-one association to PairTb
	@OneToMany(mappedBy="groupTb")
	private List<PairTb> pairTbs;

	public GroupTb() {
	}

	public GroupTb(String groupName) {
		super();
		this.groupName = groupName;
	}

	public int getRowId() {
		return this.rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<PairTb> getPairTbs() {
		return this.pairTbs;
	}

	public void setPairTbs(List<PairTb> pairTbs) {
		this.pairTbs = pairTbs;
	}

	public PairTb addPairTbs(PairTb pairTbs) {
		getPairTbs().add(pairTbs);
		pairTbs.setGroupTb(this);

		return pairTbs;
	}

	public PairTb removePairTbs(PairTb pairTbs) {
		getPairTbs().remove(pairTbs);
		pairTbs.setGroupTb(null);

		return pairTbs;
	}


}