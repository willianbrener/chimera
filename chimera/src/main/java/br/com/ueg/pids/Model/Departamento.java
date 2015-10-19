package br.com.ueg.pids.Model;

import br.com.ueg.pids.Annotations.Campo;
import br.com.ueg.pids.Annotations.Table;

@Table(nome="departamento")
public class Departamento extends  GenericModel<Integer>{
	
	@Campo(nome="iddepartamento", pk=true)
	private int iddepartamento;
	
	@Campo(nome="nome",obrigatorio=true)
	private String nome;
	
	@Campo(nome="responsavel",obrigatorio=true)
	private String responsavel;
	
	@Campo(nome="nivel",obrigatorio=true)
	private String nivel;
	
	@Campo(nome="ativo",obrigatorio=true)
	private boolean ativo;
	
	public Departamento(){
		
	}

	public Departamento(int iddepartamento, String nome, String responsavel, String nivel,Boolean ativo) {
		this.iddepartamento = iddepartamento;
		this.nome= nome;
		this.responsavel = responsavel;
		this.nivel = nivel;
		this.ativo = ativo;
	}
	
	public int getIddepartamento() {
		return iddepartamento;
	}

	public void setIddepartamento(int iddepartamento) {
		this.iddepartamento = iddepartamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
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
