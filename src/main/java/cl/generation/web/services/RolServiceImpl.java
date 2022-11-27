package cl.generation.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.generation.web.models.Rol;
import cl.generation.web.repositories.RolRepository;

@Service
public class RolServiceImpl implements RolService{
	@Autowired
	private RolRepository rolRepository;
	
	@Override
	public Rol obtenerRol(Long id) {
		Boolean existe = rolRepository.existsById(id);
		
		if (existe) {
			return rolRepository.findById(id).get();			
		}
		return null;
	}
}
