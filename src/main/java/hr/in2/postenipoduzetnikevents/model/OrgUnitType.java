
package hr.in2.postenipoduzetnikevents.model;

import javax.persistence.*;

import lombok.Data;

/**
 * Entity klasa koja odgovara TIP_ORGANIZACIJSKE_JEDINICE tablici
 * @author Nikola Gereci
 * @since 0.0.1-SNAPSHOT
 */
@Entity
@Data
@Table(name = "TIP_ORGANIZACIJSKE_JEDINICE")
public class OrgUnitType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "NAZIV")
	private String name;
	
	@Column(name = "AKTIVAN")
	private boolean active;

}
