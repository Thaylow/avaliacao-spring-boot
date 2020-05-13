package br.com.tokiomarine.seguradora.avaliacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudanteService;

@RestController
@RequestMapping("/api/estudantes")
public class EstudanteRestController {

    private final EstudanteService service;

    public EstudanteRestController(EstudanteService service) {
        this.service = service;
    }

    @RequestMapping(value = "/cadastrar", method =  RequestMethod.POST)
    public void Post(@Valid @RequestBody Estudante estudante){
        service.cadastrarEstudante(estudante);
    }

    @RequestMapping(value = "/editar/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Estudante> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Estudante newEstudante){
        newEstudante.setId(id);
        service.atualizarEstudante(newEstudante);
        return new ResponseEntity<>(newEstudante, HttpStatus.OK);
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public List<Estudante> Get(){
        return service.buscarEstudantes();
    }

    @RequestMapping(value = "/apagar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
        Estudante estudante = service.buscarEstudante(id);
        if(estudante != null){
            service.apagarEstudante(estudante.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
