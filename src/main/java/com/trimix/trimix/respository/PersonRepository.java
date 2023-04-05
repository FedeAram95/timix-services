package com.trimix.trimix.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trimix.trimix.entities.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long>  {

	@Query(value= "SELECT * FROM persona p\r\n"
			+ "WHERE p.\"ID\" = :name", nativeQuery = true)
	Page<PersonEntity> getPersonByName(@Param("name") String name, Pageable pr);
	
	@Query(value= "SELECT p.* FROM persona p\r\n"
			+ "JOIN tipo_documento tp ON tp.\"ID\"= p.\"DOCUMENT_TYPE\" \r\n"
			+ "WHERE p.\"DOCUMENT_TYPE\" = :type", nativeQuery = true)
	Page<PersonEntity> getPersonByTypeDocument(@Param("type") Long type, Pageable page);
	
}
