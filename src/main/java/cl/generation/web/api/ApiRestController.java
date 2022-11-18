package cl.generation.web.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//rutas estaticas
@RestController // no necesita un jsp, devuelve inmediatamente la info al usuario
@RequestMapping("/api") //cib RequestMapping podemos establecer una ruta 
public class ApiRestController {
	// http://localhost:8080/api/hola
	@RequestMapping("/hola")
	public String hola() {
		return "hola api";
	}
	
	//ruta dinamica: para capturar algun valor que varia en el tiempo
	// http://localhost:8080/api/edad/42
	@RequestMapping("/edad/{edad}")
	public String rutaDinamica(@PathVariable("edad") int edad) { //el pathvariable captura el valor dinamico para la ruta, luego se le asigna a la variable int edad
		return "capturando edad: "+edad;
	}
	
	// http://localhost:8080/api/nombre/javier
	@RequestMapping("/nombre/{nombre}")
	public String capturarNombre(@PathVariable("nombre") String nombre) { 
		return "capturando nombre: "+nombre;
	}
	
	// http://localhost:8080/api/14/noviembre/2022
	@RequestMapping("/{dia}/{mes}/{año}")
	public String capturarFecha(@PathVariable("dia") int dia, @PathVariable("mes") String mes,@PathVariable("año") int año) { 
		return "capturando fecha: "+dia+"/"+mes+"/"+año;
	}
	
	//http://localhost:8080/api/usuario?usuarioId=1 //aqui estamos pasando un parametro, no es la url //GET
	@RequestMapping("/usuario")
	public String parametro(@RequestParam(value="usuarioId", required = false) Integer  id ) { //estos siempre seran String. El @RequestParam es para capturar el valor del parametro
																								// required = true quiere decir que el parametro es obligatorio
		//variables primitivas como int,long,float el valor por default es 0, nunca serán null. 
		// variables tipo objeto Integer, Float, Long ... si pueden ser null
		if(id == null) {  
			return "parametro no existe";
		} else {
			return "parametro por get "+ id;			
		}
	}
	
	//http://localhost:8080/api/usuario2?usuarioId=1&nombre=javier //concatenando otro parametro (nombre)
		@RequestMapping("/usuario2")
		public String parametro2(@RequestParam(value="usuarioId", required = false) Integer  id,
				@RequestParam(value="nombre", required = false) String  nombre) { 
			if(id == null) {  
				return "parametro no existe en parametro2";
			} else {
				return "parametro por get "+ id+ " nombre: " +nombre;			
			}
		}
}
