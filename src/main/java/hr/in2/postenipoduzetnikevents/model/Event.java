
package hr.in2.postenipoduzetnikevents.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Entity klasa koja odgovara DOGADJAJ tablici
 * @author Nikola Gereci
 * @since 0.0.1-SNAPSHOT
 */

@Entity
@Data
@Table(name = "DOGADJAJI")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAZIV")
	private String name;
	
	@Column(name = "VRIJEME_OD")
	@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
	private LocalDateTime timeFrom;

	@Column(name = "VRIJEME_DO")
	@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
	private LocalDateTime timeTo;
	
	@Column(name = "SLOBODAN_ULAZ")
	private boolean free;
	
	@ManyToOne
	@JoinColumn(name = "GRAD_ID")
	private City city;


	public Event() {
		this.setId(new Long(0L));
		this.city = new City();
	}
}
