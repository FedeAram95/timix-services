package com.trimix.trimix.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trimix.trimix.dto.PersonDTO;
import com.trimix.trimix.entities.PersonEntity;
import com.trimix.trimix.respository.PersonRepository;
import com.trimix.trimix.utils.PersonConverter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonService {

	
	private PersonConverter personConverter;

	@Autowired
	private PersonRepository personRepository;
	
	

	public ResponseEntity<Object> getPerson(Integer page, Integer pageLength, String orderBy, String orientation,
			String name, Long type) {

		page = page == null ? 0 : page;
		pageLength = pageLength == null ? 10 : pageLength;

		PageRequest pr = null;
		if (orderBy != null && orientation != null) {
			orderBy = orderBy.replace('-', '.');
			if (orientation.equalsIgnoreCase("asc")) {
				pr = PageRequest.of(page, pageLength, Sort.by(Sort.Direction.ASC, orderBy));
			} else {
				if (orientation.equalsIgnoreCase("desc")) {
					pr = PageRequest.of(page, pageLength, Sort.by(Sort.Direction.DESC, orderBy));
				}
			}
		} else {
			pr = PageRequest.of(page, pageLength);
		}
		
		Page<PersonEntity> pagedEntities = null;
		if(name != null && !name.isEmpty()) {
			pagedEntities = personRepository.getPersonByName(name, pr);
		}
		if(type!= null) {
			pagedEntities = personRepository.getPersonByTypeDocument(type, pr);
		}
		else {
			pagedEntities = personRepository.findAll(pr);
		}

		List<PersonDTO> personDTO = new ArrayList<>();
		for (PersonEntity person : pagedEntities.getContent()) {
			PersonDTO dto = personConverter.dtoToEntity(person);
			personDTO.add(dto);
		}
		Page<PersonDTO> result = new PageImpl<>(personDTO, pr, pagedEntities.getTotalElements());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	
    @Transactional
    public ResponseEntity<Object> createPerson(PersonDTO newPerson) {
        try {
            PersonEntity personEntity = personConverter.entityToDTO(newPerson);
            personEntity = personRepository.saveAndFlush(personEntity);

            PersonDTO response = personConverter.dtoToEntity(personEntity);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
            
        } catch (Exception err) {
            log.error("Error al crear Rol: " + err.getMessage(), err);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Transactional
    public ResponseEntity<Object> updatePerson (PersonDTO updatedPerson, Long id) {
        try {
            PersonEntity oldPerson = personRepository.getOne(id);
            personRepository.saveAndFlush(oldPerson);
            log.info("Persona ID: {} actualizado", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception err) {
            log.error("Error al actualizar Persona: " + err.getMessage(), err);
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    public ResponseEntity<Object> deletePerson(Long id) {
        try {
            boolean exists = personRepository.existsById(id);
            if (!exists) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                personRepository.deleteById(id);
                log.info("Persona ID: " + id + " eliminado");
                return new ResponseEntity<>( HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error("Ocurrio un error: " + e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
