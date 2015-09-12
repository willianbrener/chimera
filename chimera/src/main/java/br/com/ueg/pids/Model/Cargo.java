package br.com.ueg.pids.Model;

import br.com.ueg.pids.Annotations.Table;
import br.com.ueg.pids.Annotations.Campo;


@Table(nome="aluno")
public class Cargo extends GenericModel<Integer> implements Cloneable{

	@Campo(nome="idcargo", pk=true)
	private int idcargo;
	
	@Campo(nome="name",obrigatorio=true)
	private String name;
	
	@Campo(nome="descricao",obrigatorio=true)
	private String descricao;
	
	@Campo(nome="departamento",obrigatorio=true)
	private String departamento;




	public Cargo() {
	}

	

	public int getIdcargo() {
		return idcargo;
	}



	public void setIdcargo(int idcargo) {
		this.idcargo = idcargo;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public String getDepartamento() {
		return departamento;
	}



	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}



	public String getOrdenacao() {
		return "nome";
	}

	@Override
	public String getVariaveisPesquisarNome() {
		return "nome";
	}
}
