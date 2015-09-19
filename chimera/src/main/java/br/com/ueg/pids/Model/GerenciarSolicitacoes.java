package br.com.ueg.pids.Model;

import java.util.Date;

import br.com.ueg.pids.Annotations.Campo;
import br.com.ueg.pids.Annotations.Table;


@Table(nome="gerenciar_solicitacoes")
public class GerenciarSolicitacoes extends GenericModel<Integer>{

	@Campo(nome="idgerenciar_solicitacoes", pk=true)
	private int idgerenciar_solicitacoes;
	
	@Campo(nome="titulo",obrigatorio=true)
	private String titulo;
	
	@Campo(nome="descricao",obrigatorio=true)
	private String descricao;
	
	@Campo(nome="data", obrigatorio=true)
	private Date data;
	
	@Campo(nome="hora", obrigatorio=true)
	private String hora;
	
	@Campo(nome="idusuario",obrigatorio=true)
	private Usuario idusuario;
	
	@Campo(nome="recurso", obrigatorio=true)
	private Recurso idrecurso;
	
	@Campo(nome="ativo",obrigatorio=true)
	private boolean ativo;
	
	public GerenciarSolicitacoes(){
		
	}
	
	public GerenciarSolicitacoes(int idgerenciar_solicitacoes, String titulo, String descricao,
			Date data, String hora,
			Usuario idusuario, Recurso idrecurso ,Boolean ativo) {
		this.idgerenciar_solicitacoes = idgerenciar_solicitacoes;
		this.titulo = titulo;
		this.descricao = descricao;
		this.data = data;
		this.hora = hora;
		this.idusuario = idusuario;
		this.idrecurso = idrecurso;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Usuario getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Usuario idusuario) {
		this.idusuario = idusuario;
	}

	public Recurso getIdrecurso() {
		return idrecurso;
	}

	public void setIdrecurso(Recurso idrecurso) {
		this.idrecurso = idrecurso;
	}

	public boolean isAtivo() {
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
