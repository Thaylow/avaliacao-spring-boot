package br.com.tokiomarine.seguradora.avaliacao.controller;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudanteService;

@Controller
@RequestMapping("/estudantes/")
public class EstudanteController {

	private EstudanteService service;

	public EstudanteController(EstudanteService service){
	    this.service = service;
    }

	@GetMapping("criar")
	public String iniciarCastrado(Estudante estudante) {
		return "cadastrar-estudante";
	}

	@GetMapping("listar")
	public String listarEstudantes(Model model) {
		model.addAttribute("estudantes", service.buscarEstudantes());
		return "index";
	}

	@PostMapping("add")
	public String adicionarEstudante(@ModelAttribute(value = "estudante") @Valid Estudante estudante, BindingResult result) {
		if (result.hasErrors()) {
			return "cadastrar-estudante";
		}
		service.cadastrarEstudante(estudante);
		return "redirect:listar";
	}

	@GetMapping("editar/{id}")
	public String exibirEdicaoEstudante(@PathVariable("id") long id, Model model) {
		Estudante estudante = service.buscarEstudante(id);
		model.addAttribute("estudante", estudante);
		return "atualizar-estudante";
	}

	@PostMapping("atualizar/{id}")
	public String atualizarEstudante(@PathVariable("id") long id, @Valid Estudante estudante, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "atualizar-estudante";
		}
		estudante.setId(id);
		service.atualizarEstudante(estudante);
		model.addAttribute("estudantes", service.buscarEstudantes());
		return "index";
	}

	@GetMapping("apagar/{id}")
	public String apagarEstudante(@PathVariable("id") long id, Model model) {
	    service.apagarEstudante(id);
		model.addAttribute("estudantes", service.buscarEstudantes());
		return "index";
	}
}
