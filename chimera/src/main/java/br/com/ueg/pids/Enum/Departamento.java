package br.com.ueg.pids.Enum;

public enum Departamento {
	FINANCEIRO(1,"Financeiro"), 
	CONVENIO(2,"Convenio"), 
	COBRANCA(3,"Cobranca"),
	GERENCIA(4,"Gerencia");
	
	private Integer id;
	private String descricao;

	private Departamento(Integer id, String descricao) {
		this.setId(id);
		this.setDescricao(descricao);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
