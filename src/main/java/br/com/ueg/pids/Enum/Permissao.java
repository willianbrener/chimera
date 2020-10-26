package br.com.ueg.pids.Enum;

public enum Permissao {

	SOLICITANTE(1, "SOLICITANTE"), 
	APROVADOR(2, "APROVADOR"), 
	EXECUTOR(3,"EXECUTOR");
	
	private Integer id;
	private String nome;

	private Permissao(Integer id, String nome) {
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
