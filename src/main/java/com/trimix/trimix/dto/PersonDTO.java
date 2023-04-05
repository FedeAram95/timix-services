package com.trimix.trimix.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {
	
	 Long id;
	 String name;
	 String lastName;
	 String document;
	 TypeDocumentDTO typeDocument;
	 Date date;
}
