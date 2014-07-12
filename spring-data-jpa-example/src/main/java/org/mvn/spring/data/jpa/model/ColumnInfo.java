package org.mvn.spring.data.jpa.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
@NamedQuery(name="ColumnInfo.findByTheColumninfoId",query=" from ColumnInfo a where a.columninfoId = ?1 ")
public class ColumnInfo {

	private int columninfoId;
	private String colname;
	private Set<ContentInfo> sets;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getColumninfoId() {
		return columninfoId;
	}

	public void setColumninfoId(int columninfoId) {
		this.columninfoId = columninfoId;
	}

	public String getColname() {
		return colname;
	}

	public void setColname(String colname) {
		this.colname = colname;
	}

	@OneToMany(mappedBy="columnInfo",cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	public Set<ContentInfo> getSets() {
		return sets;
	}

	public void setSets(Set<ContentInfo> sets) {
		this.sets = sets;
	}

}
