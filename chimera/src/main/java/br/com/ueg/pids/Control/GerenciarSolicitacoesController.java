package br.com.ueg.pids.Control;

import java.sql.SQLException;
import java.util.List;

import br.com.ueg.pids.Colections.ColecaoSolicitacoes;
import br.com.ueg.pids.Colections.ColecaoUsuario;
import br.com.ueg.pids.Model.GerenciarSolicitacoes;
import br.com.ueg.pids.Model.IModel;
import br.com.ueg.pids.Model.Usuario;
import br.com.ueg.pids.Utils.Return;

public class GerenciarSolicitacoesController extends GenericController<GerenciarSolicitacoes> {

	public List<?> getLstUsuarioDados(String keyword) {
		IModel<?> usuario = (IModel<?>) new Usuario();
		ColecaoUsuario colecaoUsuario = new ColecaoUsuario();
		try {
			colecaoUsuario.setAll(dao.pesquisarNome(usuario, keyword));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoUsuario.getAll();
	}
	@Override
	public List<?> getLstEntities(String keyword) {
		IModel<?> solicitacoes = (IModel<?>) new GerenciarSolicitacoes();
		ColecaoSolicitacoes colecaoSolicitacoes = new ColecaoSolicitacoes();
		try {
			colecaoSolicitacoes.setAll(dao.pesquisarNome(solicitacoes, keyword));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colecaoSolicitacoes.getAll();
	}
	
	@Override
	public Return validar(IModel<?> imodel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Return validarItemUnico(IModel<?> imodel) {
		// TODO Auto-generated method stub
		return null;
	}

}
