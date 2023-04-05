package com.trimix.trimix.utils;

import com.trimix.trimix.dto.PersonDTO;
import com.trimix.trimix.entities.PersonEntity;

public class PersonConverter {

	  public PersonDTO dtoToEntity(PersonEntity entity) {
		  
		  PersonDTO dto = new PersonDTO();
		  
		  if(entity != null) {
				  dto.setId(entity.getId());
				  dto.setName(entity.getName());
				  dto.setLastName(entity.getLastName());
				  dto.getTypeDocument().setId(entity.getTypeDocument().getId());
				  dto.setDocument(entity.getDocument());
				  dto.setDate(entity.getDate());
			  } 
		return dto;
		}
	  
	  
	  public PersonEntity entityToDTO(PersonDTO dto) {
		  
		  PersonEntity entity = new PersonEntity();
		  
		  if(dto != null) {
			  entity.setId(dto.getId());
			  entity.setName(dto.getName());
			  entity.setLastName(dto.getLastName());
			  entity.getTypeDocument().setId(dto.getTypeDocument().getId());
			  entity.setDocument(dto.getDocument());
			  entity.setDate(dto.getDate());
			  } 
		return entity;
		}
}
