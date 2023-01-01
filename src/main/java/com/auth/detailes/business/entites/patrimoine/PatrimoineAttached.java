package com.auth.detailes.business.entites.patrimoine;

import com.auth.detailes.business.entites.common.DateAudit;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class PatrimoineAttached extends DateAudit implements Serializable{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nom")
	private String label;

	@EqualsAndHashCode.Exclude @ToString.Exclude
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "patrimoine_attached_id", referencedColumnName = "id")
	private Patrimoine patrimoineAttached;

	@EqualsAndHashCode.Exclude @ToString.Exclude
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "patrimoine_id", referencedColumnName = "id")
	private Patrimoine patrimoine;

	@EqualsAndHashCode.Exclude @ToString.Exclude
	@ManyToOne
	private TypeRelation typeRelation;

	@Column(name = "createdBy")
	private Long createdBy;
}
