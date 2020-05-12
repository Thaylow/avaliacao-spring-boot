package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EstudanteServiceImpl implements EstudanteService {

	private EstudanteRepository repository;

	public EstudanteServiceImpl(EstudanteRepository repository){
		this.repository = repository;
	}

	@Override
	public void cadastrarEstudante(@Valid Estudante estudante) {
		repository.save(estudante);
	}

	@Override
	public void atualizarEstudante(@Valid Estudante estudante) {
		Optional<Estudante> oldEstudante = repository.findById(estudante.getId());
		if(oldEstudante.isPresent()){
			Estudante newEstudante = oldEstudante.get();
			newEstudante.setNome(estudante.getNome());
			newEstudante.setEmail(estudante.getEmail());
			newEstudante.setTelefone(estudante.getTelefone());
			newEstudante.setMatricula(estudante.getMatricula());
			newEstudante.setCurso(estudante.getCurso());
			repository.save(estudante);
		}

	}

	@Override
	public List<Estudante> buscarEstudantes() {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
	}

	@Override
	public Estudante buscarEstudante(long id) {
		if(id == 0L ){
			throw new IllegalArgumentException("Identificador inválido:" + id);
		}

		Optional<Estudante> estudante = repository.findById(id);

		//todo - verificar se a lista é vazia e lançar exceção

		return estudante.get();
	}

	public void apagarEstudante(long id){
		if(id == 0L ){
			throw new IllegalArgumentException("Identificador inválido:" + id);
		}
		Optional<Estudante> estudante = repository.findById(id);
		if(estudante.isPresent()){
			repository.delete(estudante.get());
		}

	}

}
