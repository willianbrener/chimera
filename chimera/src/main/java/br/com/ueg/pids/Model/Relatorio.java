package br.com.ueg.pids.Model;

import java.util.Date;


public class Relatorio extends GenericModel<Integer>{
	
	private Date dataInicial;
	private Date dataFinal;
	private Departamento departamento;

	public String getOrdenacao() {
		return null;
	}

	@Override
	public String getVariaveisPesquisarNome() {
		return null;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

}
