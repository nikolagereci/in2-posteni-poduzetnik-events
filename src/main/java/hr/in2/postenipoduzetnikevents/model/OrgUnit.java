
package hr.in2.postenipoduzetnikevents.model;

import java.util.List;

import javax.persistence.*;

import lombok.Data;

/**
 * Entity klasa koja odgovara ORGANIZACIJSKA_JEDINICA tablici
 * @author Nikola Gereci
 * @since 0.0.1-SNAPSHOT
 */
@Entity
@Data
@Table(name = "ORGANIZACIJSKA_JEDINICA")
public class OrgUnit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "NAZIV")
	private String name;
	
	@Column(name = "OPIS")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "TIP_ORG_JED_ID")
	private OrgUnitType orgUnitType;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ORG_JED_ID" , insertable = false, updatable = false)
    private OrgUnit parent;
	
	@OneToMany(mappedBy = "parent")
	private List<OrgUnit> children;

	@OneToMany(mappedBy = "city")
	private List<City> cities;
}