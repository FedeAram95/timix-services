package com.trimix.trimix.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table( name = "persona")
public class PersonEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
	private Long id;
	
    @Column(name = "NAME")
	private String name;
	
    @Column(name = "LAST_NAME")
	private String lastName;
	
    @Column(name = "DOCUMENT")
	private String document;
	
    @ManyToOne
    @JoinColumn(name = "DOCUMENT_TYPE", referencedColumnName = "ID")
	private TypeDocumentEntity typeDocument;
	
    @Column(name = "DATE")
	private Date date;
}
