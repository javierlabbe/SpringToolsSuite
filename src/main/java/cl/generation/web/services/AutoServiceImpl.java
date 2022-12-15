package cl.generation.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.generation.web.models.Auto;
import cl.generation.web.repositories.AutoRepository;

@Service
public class AutoServiceImpl implements AutoService {
	@Autowired
	private AutoRepository autoRepository;

	@Override
	public Auto guardarAuto(Auto auto) {
		return autoRepository.save(auto);
	}

	@Override
	public Auto obtenerAuto(Long id) {
		Boolean existe = autoRepository.existsById(id);

		if (existe) {
			Auto auto = autoRepository.findById(id).get();
			return auto;
		}
		return null;
	}

	@Override
	public List<Auto> listaAutos() {
		return autoRepository.findAll();
	}
	
	@Override
	public Auto obtenerAutoNombre(String marca) {
		return autoRepository.findByMarca(marca);
	}
}
