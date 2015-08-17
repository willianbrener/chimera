package br.com.ueg.pids.Model;



public class Cargo extends GenericModel implements Cloneable{

	
	private String name;
	private String descricao;
	
	private String departamento;




	public Cargo() {
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

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
