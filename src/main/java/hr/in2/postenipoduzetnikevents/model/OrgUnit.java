
package hr.in2.postenipoduzetnikevents.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "ORGANIZACIJSKA_JEDINICA")
public class OrgUnit {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	
	@Column(name = "NAZIV")
	private String name;
	
	@Column(name = "OPIS")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "TIP_ORGANIZACIJSKE_JEDINICE_ID")
	private OrgUnitType orgUnitType;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ORG_JED_ID")
    private OrgUnit parent;
	
	@OneToMany(mappedBy = "parent")
	private List<OrgUnit> children;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "GRAD_ID")
	private City city;
}
