package cl.generation.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.generation.web.models.Auto;
import cl.generation.web.services.AutoServiceImpl;

@Controller
@RequestMapping("/home") //aqui va RequestMapping
public class HomeController { //encargado de desplegar home.jsp
	@Autowired //para inyectar metodos de otra clase
	AutoServiceImpl autoServiceImpl;
	
	//localhost:8080/home
	@GetMapping("") //peticion de ruta para despliegue, comillas vacias porque por default son /home
	public String home(Model model, HttpSession session) { //Long id Model model para transpasar info del controlador al jsp (backend a frontend)
		
		if (session.getAttribute("usuarioId")!=null) { //comprobacion para ver si el usuario esta logeado y dejarlo entrar a home
			
			//capturando variables de session
			String email = (String) session.getAttribute("usuarioEmail");
			Long usuarioId = (Long) session.getAttribute("usuarioId");
			String nombre = (String) session.getAttribute("usuarioNombre");
			
			//obtener y almacenar en variable
			List<Auto> listaAutos = autoServiceImpl.listaAutos();
			
			List<Auto> listaSelectAutos= autoServiceImpl.listaAutos();
			//pasar lista al jsp		
			model.addAttribute("listaSelectAutos", listaSelectAutos);
			model.addAttribute("listaAutos", listaAutos);
			
			model.addAttribute("usuarioNombre", nombre);
			
			return "home.jsp";			
		} else {
			return "redirect:/registro/login";
		}
		
		
	}
	
	//ruta postmapping respondera cuando estemos entregando un valor por formulario (post)
	@PostMapping("")
	public String filtrar(@RequestParam("autoSeleccionado") Long id, Model model) {
		List<Auto> listaAutos = new ArrayList<Auto>(); //generando lista vacia
		Auto auto = autoServiceImpl.obtenerAuto(id);
		listaAutos.add(auto); //agrego auto a la lista
		model.addAttribute("listaAutos", listaAutos);
		//lista para el selector
		List<Auto> listaSelectAutos= autoServiceImpl.listaAutos();
		//pasar lista al jsp		
		model.addAttribute("listaSelectAutos", listaSelectAutos);
		return "home.jsp";
	}
	
	@PostMapping("/nav")
	public String filtrarNav(@RequestParam("marca") String marca, Model model) {
		List<Auto> listaAutos = new ArrayList<Auto>(); //generando lista vacia
		Auto auto = autoServiceImpl.obtenerAutoNombre(marca);
		listaAutos.add(auto); //agrego auto a la lista
		model.addAttribute("listaAutos", listaAutos);
		//lista para el selector
		List<Auto> listaSelectAutos= autoServiceImpl.listaAutos();
		//pasar lista al jsp		
		model.addAttribute("listaSelectAutos", listaSelectAutos);
		return "home.jsp";
	}
}
