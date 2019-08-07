
package hr.in2.postenipoduzetnikevents.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "DOGADJAJ")
public class Event {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	
	@Column(name = "NAZIV")
	private String name;
	
	@Column(name = "VRIJEME_OD")
	private LocalDateTime timeFrom;
	
	@Column(name = "VRIJEME_DO")
	private LocalDateTime timeTo;
	
	@Column(name = "SLOBODAN_ULAZ")
	private boolean free;
	
	@ManyToOne
	@JoinColumn(name = "GRAD_ID")
	private City city;
}
