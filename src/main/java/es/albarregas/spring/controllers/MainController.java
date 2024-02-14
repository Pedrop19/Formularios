package es.albarregas.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.albarregas.spring.models.Alumno;
import es.albarregas.spring.services.AlumnoService;

@Controller
public class MainController {
	
	@Autowired
	private AlumnoService service;
	
	@GetMapping({"alumno/list"})
	public String listado(Model model) {
		model.addAttribute("listadoAlumnos", service.findAll());
		return "list";
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}

	
	@GetMapping("alumno/new")
	public String nuevoAlumnoForm(Model model) {
		model.addAttribute("alumnoForm", new Alumno());
		return "new";
	}

	@GetMapping("alumno/edit/{id}")
	public String editarAlumnoForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("alumnoForm", service.findById(id));
		return "edit";
	}

	@GetMapping("alumno/delete/{id}")
	public String borrarAlumno(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("alumnoForm", service.findById(id));
		return "delete";
	}

	@PostMapping("alumno/delete/submit")
	public String borrarAlumnoSubmit(@ModelAttribute("alumnoForm") Alumno alumno) {
		service.delete(alumno.getId());
		return "index";
	}

	@PostMapping("/alumno/edit/submit")
	public String editarAlumnoSubmit(@ModelAttribute("alumnoForm") Alumno alumno) {
		service.update(alumno);
		return "index";
	}

	
	@PostMapping("/alumno/new/submit")
	public String nuevoAlumnoSubmit(@ModelAttribute("alumnoForm") Alumno nuevoAlumno) {
		service.add(nuevoAlumno);
		return "index";
	}

}
