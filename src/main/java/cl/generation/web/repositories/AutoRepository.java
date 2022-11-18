package cl.generation.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.generation.web.models.Auto;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long>{
	
}

