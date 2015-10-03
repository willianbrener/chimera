package br.com.ueg.pids.Model;


import br.com.ueg.pids.Annotations.Campo;
import br.com.ueg.pids.Annotations.Table;


@Table(nome="solicitacoes")
public class GerenciarSolicitacoes extends GenericModel<Integer>{

	@Campo(nome="idsolicitacoes", pk=true)
	private int idgerenciar_solicitacoes;
	
	@Campo(nome="titulo",obrigatorio=true)
	private String titulo;
	
	@Campo(nome="descricao",obrigatorio=true)
	private String descricao;

	@Campo(nome="permissao",obrigatorio=true)
	private String permissao;
	
	@Campo(nome="data", obrigatorio=true)
	private String data;
	
	@Campo(nome="hora", obrigatorio=true)
	private String hora;
	
	@Campo(nome="idusuario",obrigatorio=true)
	private Usuario usuario;
	
	@Campo(nome="idrecurso", obrigatorio=true)
	private Recurso recurso;
	
	@Campo(nome="ativo",obrigatorio=true)
	private boolean ativo;
	
	public GerenciarSolicitacoes(){
		
	}
	
	public GerenciarSolicitacoes(int idgerenciar_solicitacoes, String titulo, String descricao,
			String permissao,String data, String hora,
			Usuario usuario, Recurso recurso ,Boolean ativo) {
		this.idgerenciar_solicitacoes = idgerenciar_solicitacoes;
		this.titulo = titulo;
		this.descricao = descricao;
		this.permissao = permissao;
		this.data = data;
		this.hora = hora;
		this.usuario = usuario;
		this.recurso = recurso;
		this.ativo = ativo;
	}
	

	public int getIdgerenciar_solicitacoes() {
		return idgerenciar_solicitacoes;
	}

	public void setIdgerenciar_solicitacoes(int idgerenciar_solicitacoes) {
		this.idgerenciar_solicitacoes = idgerenciar_solicitacoes;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getOrdenacao() {
		return null;
	}

	@Override
	public String getVariaveisPesquisarNome() {
		return null;
	}

}
