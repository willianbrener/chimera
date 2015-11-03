package br.com.ueg.pids.Model;

import br.com.ueg.pids.Annotations.Campo;
import br.com.ueg.pids.Annotations.Table;

@Table(nome = "solicitacao_motivo")
public class SolicitacaoMotivo extends GenericModel<Integer> {
	@Campo(nome = "idsolicitacaomotivo", pk = true)
	private int idsolicitacaomotivo;
	
	@Campo(nome = "idsolicitacoes", obrigatorio = true)
	private GerenciarSolicitacoes solicitacoes;

	@Campo(nome = "idmotivo", obrigatorio = true)
	private Motivo motivo;

	@Campo(nome = "data", obrigatorio = true)
	private String data;

	public SolicitacaoMotivo() {
		this.motivo = new Motivo();
	}

	public SolicitacaoMotivo(int idsolicitacaomotivo, String data, GerenciarSolicitacoes solicitacoes,
			Motivo motivo) {
		this.idsolicitacaomotivo = idsolicitacaomotivo;
		this.data = data;
		this.solicitacoes = solicitacoes;
		this.motivo = motivo;
	}

	public GerenciarSolicitacoes getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(GerenciarSolicitacoes solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	public int getIdsolicitacaomotivo() {
		return idsolicitacaomotivo;
	}

	public void setIdsolicitacaomotivo(int idsolicitacaomotivo) {
		this.idsolicitacaomotivo = idsolicitacaomotivo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getOrdenacao() {
		return null;
	}

	@Override
	public String getVariaveisPesquisarNome() {
		return null;
	}

}
