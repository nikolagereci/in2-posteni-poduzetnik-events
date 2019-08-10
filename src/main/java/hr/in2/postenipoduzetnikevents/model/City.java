
package hr.in2.postenipoduzetnikevents.model;

import javax.persistence.*;

import lombok.Data;

import java.util.List;

/**
 * Entity klasa koja odgovara GRAD tablici
 * @author Nikola Gereci
 * @since 0.1.0
 */

@Entity
@Data
@Table(name = "GRAD")
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "NAZIV")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "VELICINA_GRADA_ID")
	private CitySize citySize;
	
	@ManyToOne
	@JoinColumn(name = "ORG_JED_ID")
	private OrgUnit orgUnit;

	@OneToMany(mappedBy = "city")
	List<Event> city;
}
