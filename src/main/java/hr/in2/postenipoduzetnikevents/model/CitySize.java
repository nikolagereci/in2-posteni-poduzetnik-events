
package hr.in2.postenipoduzetnikevents.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "VELICINA_GRADA")
public class CitySize {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	
	@Column(name = "NAZIV")
	private String name;
	
	@Column(name = "AKTIVAN")
	private boolean active;

}
