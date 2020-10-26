package br.com.ueg.pids.Enum;

public enum SituacaoSolicitacao {
	APROVADA(1, "APROVADA"), 
	EXECUTADA(2, "EXECUTADA"), 
	REPROVADA(3,"REPROVADA"),
	PENDENTE(4,"PENDENTE");
	
	private Integer id;
	private String nome;

	private SituacaoSolicitacao(Integer id, String nome) {
		this.setId(id);
		this.setNome(nome);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
