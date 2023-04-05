package com.trimix.trimix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trimix.trimix.dto.PersonDTO;
import com.trimix.trimix.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	
    @GetMapping("/person")
    public ResponseEntity<Object> getPerson(@RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "length", required = false) Integer length,
            @RequestParam(value = "orderBy", required = false) String orderBy,
            @RequestParam(value = "orientation", required = false) String orientation,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "type", required = false) Long type) {
        return personService.getPerson(page, length, orderBy, orientation, name, type);
    }
	
    @PostMapping("/person")
    public ResponseEntity<Object> createPerson(@RequestBody() PersonDTO personDTO) {
        return personService.createPerson(personDTO);
    }
    
    @PutMapping("/person/{id}")
    public ResponseEntity<Object> updatePerson(@RequestBody() PersonDTO personDTO, @PathVariable(value = "id") Long id) {
        return personService.updatePerson(personDTO, id);
    }
    
    @DeleteMapping("/person/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable(value = "id") Long id) {
        return deletePerson(id);
    }

}
