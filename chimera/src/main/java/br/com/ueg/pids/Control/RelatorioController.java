package br.com.ueg.pids.Control;

import java.sql.SQLException;
import java.util.List;

import br.com.ueg.pids.Colections.ColecaoGerenciarSolicitacoes;
import br.com.ueg.pids.Model.Departamento;
import br.com.ueg.pids.Model.GerenciarSolicitacoes;
import br.com.ueg.pids.Model.IModel;
import br.com.ueg.pids.Model.Relatorio;
import br.com.ueg.pids.Utils.Return;


public class RelatorioController extends GenericController<Relatorio> {

	public List<GerenciarSolicitacoes> getLstData(String coluna, String dataIni, String dataFim) {
		
		GerenciarSolicitacoes solicitacoes = new GerenciarSolicitacoes();
		ColecaoGerenciarSolicitacoes colecaoSolicitacoes = new ColecaoGerenciarSolicitacoes();
		try {
			colecaoSolicitacoes.setAll(dao.pesquisarCriteriosData(solicitacoes,coluna,dataIni,dataFim));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return colecaoSolicitacoes.getAll();
	}
	
	public List<GerenciarSolicitacoes> getLstDepart(String coluna, Departamento departamento) {
		
		IModel<?> solicitacoes = (IModel<?>) new GerenciarSolicitacoes();
		ColecaoGerenciarSolicitacoes colecaoSolicitacoes = new ColecaoGerenciarSolicitacoes();
		try {
			String str = String.valueOf(departamento.getIddepartamento()); 
			colecaoSolicitacoes.setAll(dao.pesquisarCriterio(solicitacoes,coluna,str));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return colecaoSolicitacoes.getAll();
	}
	
	@Override
	public Return validar(IModel<?> imodel) {
		return null;
	}

	@Override
	public Return validarItemUnico(IModel<?> imodel) {
		return null;
	}
	

}
