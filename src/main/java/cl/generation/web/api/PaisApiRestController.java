package cl.generation.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.generation.web.models.Pais;
import cl.generation.web.services.PaisServiceImpl;

@RestController
@RequestMapping("/pais") //RequestMapping sirve para las rutas
public class PaisApiRestController { //Api Rest espera la consulta/solicitud
	
	@Autowired
	private PaisServiceImpl paisServiceImpl;
	
	@RequestMapping("/guardar")
	public Pais guardarPais(@RequestBody Pais pais) { //RequestBody n
		return paisServiceImpl.guardarPais(pais);
	}
	
}
