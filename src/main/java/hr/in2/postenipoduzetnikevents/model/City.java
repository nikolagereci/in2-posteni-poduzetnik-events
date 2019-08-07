
package hr.in2.postenipoduzetnikevents.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "GRAD")
public class City {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	
	@Column(name = "NAZIV")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "VELICINA_GRADA_ID")
	private CitySize citySize;
	
	@ManyToOne
	@JoinColumn(name = "ORGANIZACIJSKA_JEDINICA_ID")
	private OrgUnit orgUnit;
}
