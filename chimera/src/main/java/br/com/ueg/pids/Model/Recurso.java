package br.com.ueg.pids.Model;

import br.com.ueg.pids.Annotations.Campo;
import br.com.ueg.pids.Annotations.Table;


@Table(nome="recurso")
public class Recurso extends GenericModel<Integer>{

	@Campo(nome="idrecurso", pk=true)
	private int idrecurso;
	
	@Campo(nome="nome",obrigatorio=true)
	private String nome;
	
	@Campo(nome="descricao",obrigatorio=true)
	private String descricao;
	
	@Campo(nome="iddepartamento",obrigatorio=true)
	private Departamento departamento;
	
	@Campo(nome="ativo",obrigatorio=true)
	private boolean ativo;
	
	public Recurso(){
		
	}

	public Recurso(int idrecurso, String nome, String descricao, Departamento departamento,Boolean ativo) {
		this.idrecurso = idrecurso;
		this.nome= nome;
		this.descricao = descricao;
		this.departamento = departamento;
		this.ativo = ativo;
	}
	
	
	public int getIdrecurso() {
		return idrecurso;
	}

	public void setIdrecurso(int idrecurso) {
		this.idrecurso = idrecurso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getOrdenacao() {
		return "nome";
	}

	@Override
	public String getVariaveisPesquisarNome() {
		return "nome";
	}


}
