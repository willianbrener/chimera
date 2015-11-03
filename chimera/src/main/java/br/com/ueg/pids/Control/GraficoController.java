package br.com.ueg.pids.Control;

import java.sql.SQLException;

import br.com.ueg.pids.Colections.ColecaoUtils;
import br.com.ueg.pids.Model.GerenciarSolicitacoes;
import br.com.ueg.pids.Model.Grafico;
import br.com.ueg.pids.Model.IModel;
import br.com.ueg.pids.Utils.Return;

public class GraficoController extends GenericController<Grafico>{
	String dado;
	double resultado;
	
	public Double listaQuantia(String condicional, String colunaConta, String parametro) {
		GerenciarSolicitacoes solicitacoes = new GerenciarSolicitacoes();
		ColecaoUtils utils = new ColecaoUtils();
		
		try {
			utils.setAll(dao.pesquisarQuantidade(solicitacoes, condicional, colunaConta, parametro));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setDado(utils.getAll());
		
		resultado = Double.parseDouble(dado);
		return resultado;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Return validar(IModel imodel) {
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Return validarItemUnico(IModel imodel) {
		return null;
	}

	public String getDado() {
		return dado;
	}

	public void setDado(String dado) {
		this.dado = dado;
	}



}
