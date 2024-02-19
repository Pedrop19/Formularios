package es.albarregas.spring.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import es.albarregas.spring.models.Alumno;
import es.albarregas.spring.services.IAlumnoService;
import es.albarregas.spring.upload.storage.StorageFileNotFoundException;
import es.albarregas.spring.upload.storage.StorageService;


@Controller
public class MainController {

	@Autowired
	private IAlumnoService service;

	@Autowired
	private StorageService storageService;


	@GetMapping({ "alumno/list" })
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
		Alumno alumno = service.findById(id);
		model.addAttribute("alumnoForm", service.findById(id));
		model.addAttribute("avatar", alumno.getAvatar());
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
	public String editarAlumnoSubmit(@Valid @ModelAttribute("alumnoForm") Alumno alumno, 
			BindingResult bindingResult, @RequestParam("file") MultipartFile file){
		if (bindingResult.hasErrors()) {
			return "edit";
		} else {
			if(!file.isEmpty()) {
				String avatar = storageService.store(file, alumno.getId());
				storageService.store(file, alumno.getId());
				alumno.setAvatar(MvcUriComponentsBuilder.fromMethodName(MainController.class, "serveFile", avatar).build().toUriString());
			}
			service.update(alumno);
			return "index";
		}
	}

	@PostMapping("/alumno/new/submit")
	public String nuevoAlumnoSubmit(@Valid @ModelAttribute("alumnoForm") Alumno nuevoAlumno,
			BindingResult bindingResult, @RequestParam("file") MultipartFile file) {
		if (bindingResult.hasErrors()) {
			return "new";
		} else {
			if (!file.isEmpty()) {
				String avatar = storageService.store(file, nuevoAlumno.getId());
				nuevoAlumno.setAvatar(MvcUriComponentsBuilder
						.fromMethodName(MainController.class, "serveFile", avatar).build().toUriString());
			}
			service.add(nuevoAlumno);
			return "redirect:/";
		}
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().body(file);
	}

}
