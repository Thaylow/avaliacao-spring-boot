package br.com.tokiomarine.seguradora.avaliacao.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "estudante")
public class Estudante {

	@Id
	@Column(name="id")
	@JsonInclude(value=Include.NON_NULL)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="nome" , nullable = false)
    @NotNull(message = "Nome é obrigatório")
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Column(name="email" , nullable = false)
    @NotNull(message = "E-mail é obrigatório")
    @NotBlank(message = "E-mail é obrigatório")
    private String email;

    @Column(name="telefone")
    private String telefone;

    @Column(name="matricula" , nullable = false)
    @NotNull(message =  "Matrícula é obrigatório")
    @NotBlank(message = "Matrícula é obrigatório")
    private String matricula;

    @Column
    private String curso;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
