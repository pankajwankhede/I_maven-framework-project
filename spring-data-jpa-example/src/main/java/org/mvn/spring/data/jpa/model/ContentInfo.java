package org.mvn.spring.data.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQuery(name="ContentInfo.findByTheColumnInfoColumninfoId",query="select a from ContentInfo a where a.columnInfo.columninfoId= ?1 ")
public class ContentInfo {

	private int contentId;
	private String conname;
	private String conbody;
	private ColumnInfo columnInfo;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getConname() {
		return conname;
	}

	public void setConname(String conname) {
		this.conname = conname;
	}

	public String getConbody() {
		return conbody;
	}

	public void setConbody(String conbody) {
		this.conbody = conbody;
	}

	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="colid")
	public ColumnInfo getColumnInfo() {
		return columnInfo;
	}

	public void setColumnInfo(ColumnInfo columnInfo) {
		this.columnInfo = columnInfo;
	}

}
