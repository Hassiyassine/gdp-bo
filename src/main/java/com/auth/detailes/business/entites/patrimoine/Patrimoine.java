package com.auth.detailes.business.entites.patrimoine;

import com.auth.detailes.business.entites.common.DateAudit;
import com.auth.detailes.business.entites.common.Document;
import com.auth.detailes.business.entites.patrimoine.geo.Geometry;
import com.auth.detailes.business.entites.referentiel.TypeBien;
import com.auth.detailes.business.entites.referentiel.TypeRegim;
import com.auth.detailes.utilities.enums.Status;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class Patrimoine extends DateAudit implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nom")
	private String name;

	@EqualsAndHashCode.Exclude @ToString.Exclude
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Proprietaire proprietaire;

	@EqualsAndHashCode.Exclude @ToString.Exclude
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private TypeBien typeBien;

	@EqualsAndHashCode.Exclude @ToString.Exclude
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private TypeRegim typeRegim;

	@Column(name = "numero")
	private String numero;

	@Column(name = "superficie")
	private String superficie;

	@Column(name = "utilisation")
	private String utilisation;

	@Column(name = "ville")
	private String ville;

	@Column(name = "adresse")
	private String adresse;

	@Column(name = "affectation")
	private String affectation;

	@Column(name = "dateHomologation")
	private Date dateHomologation;

	@Column(name = "dateHomologationExpiration")
	private Date dateHomologationExpiration;

	@Column(name = "detailsApposition")
	private Date detailsApposition;

	@Column(name = "description",columnDefinition = "text")
	private String description;

	@Column(name="wkt", columnDefinition = "text")
	private String wkt;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(name = "address")
	private String address;

	@Column(name = "superficieTax")
	private String superficieTax;

	@Column(name = "typeTerrain")
	private String typeTerrain;

	@Column(name = "ratio")
	private String ratio;

	@Column(name = "privatetypeTexe")
	private String privatetypeTexe;

	@Column(name = "typeTexe")
	private String typeTexe;

	@Column(name = "isNotified")
	private Boolean isNotified = Boolean.FALSE;

	@EqualsAndHashCode.Exclude @ToString.Exclude
	@OneToMany(mappedBy = "patrimoine")
	private Set<Document> photos = new HashSet<>();

	@EqualsAndHashCode.Exclude @ToString.Exclude
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private Document geoJson;

	@EqualsAndHashCode.Exclude @ToString.Exclude
	@OneToMany(mappedBy = "patrimoineDocument")
	private Set<Document> documents = new HashSet<>();

	@EqualsAndHashCode.Exclude @ToString.Exclude
	@OneToMany(mappedBy = "patrimoine",cascade = CascadeType.REFRESH)
	private Set<Event> events = new HashSet<>();

	@Column(name = "isDeleted")
	private Boolean isDeleted;

	@EqualsAndHashCode.Exclude @ToString.Exclude
	@OneToOne(cascade = CascadeType.ALL)
	private Geometry geometry;

	@EqualsAndHashCode.Exclude @ToString.Exclude
	@OneToOne(cascade = CascadeType.ALL)
	private Contract contract;

	@EqualsAndHashCode.Exclude @ToString.Exclude
	@ManyToMany(mappedBy = "patrimoines",cascade = CascadeType.REFRESH)
	private Set<Tag> tags = new HashSet<>();

	@EqualsAndHashCode.Exclude @ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "patrimoine")
	private Set<PatrimoineAttached> patrimoineAttacheds;

	@Column(name = "createdBy")
	private Long createdBy;
}
