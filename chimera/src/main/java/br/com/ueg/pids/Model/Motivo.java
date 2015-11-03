package br.com.ueg.pids.Model;

import br.com.ueg.pids.Annotations.Campo;
import br.com.ueg.pids.Annotations.Table;

@Table(nome="motivo")
public class Motivo extends  GenericModel<Integer>{
	
	@Campo(nome="idmotivo", pk=true)
	private int idmotivo;
	
	@Campo(nome="descricao",obrigatorio=true)
	private String descricao;
	
	@Campo(nome = "idsolicitacoes", obrigatorio = true)
	private GerenciarSolicitacoes solicitacao;
	
	@Campo(nome="ativo",obrigatorio=true)
	private boolean ativo;
	
	public Motivo(){
	}

	public Motivo(int idmotivo, GerenciarSolicitacoes solicitacao,String descricao,Boolean ativo) {
		this.idmotivo = idmotivo;
		this.solicitacao = solicitacao;
		this.descricao= descricao;
		this.ativo = ativo;
	}
	
	
	public String getOrdenacao() {
		return "descricao";
	}

	@Override
	public String getVariaveisPesquisarNome() {
		return "descricao";
	}

	

	public GerenciarSolicitacoes getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(GerenciarSolicitacoes solicitacao) {
		this.solicitacao = solicitacao;
	}

	public int getIdmotivo() {
		return idmotivo;
	}

	public void setIdmotivo(int idmotivo) {
		this.idmotivo = idmotivo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	
}
