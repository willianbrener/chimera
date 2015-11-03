package br.com.ueg.pids.Control;

import java.sql.SQLException;
import java.util.List;

import org.zkoss.zul.Messagebox;

import br.com.ueg.pids.Colections.ColecaoGerenciarSolicitacoes;
import br.com.ueg.pids.Colections.ColecaoUsuario;
import br.com.ueg.pids.Model.GerenciarSolicitacoes;
import br.com.ueg.pids.Model.IModel;
import br.com.ueg.pids.Model.Usuario;
import br.com.ueg.pids.Utils.Return;

public class GerenciarSolicitacoesController extends GenericController<GerenciarSolicitacoes> {
	
	private GerenciarSolicitacoes solicitacoes = new GerenciarSolicitacoes();
	
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
	public List<GerenciarSolicitacoes> getLstEntities(String string, Usuario usuario) {
		
		IModel<?> solicitacoes = (IModel<?>) new GerenciarSolicitacoes();
		ColecaoGerenciarSolicitacoes colecaoSolicitacoes = new ColecaoGerenciarSolicitacoes();
		try {
			colecaoSolicitacoes.setAll(dao.listarSolicitacoes(solicitacoes,usuario,string));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return colecaoSolicitacoes.getAll();
	}
	
	public Return alterarSolicitacao(IModel<?> imodel, String string) {
		Return res = validarSolicitacao(imodel);
		if (res.isValid()) {
			return dao.updateSolicitacao(imodel,string);
		}
		return res;
	}
	
	@Override
	public Return validar(IModel<?> imodel) {
		setSolicitacoes((GerenciarSolicitacoes) imodel);
		Return ret = new Return(true);
		if (getSolicitacoes().getTitulo() == null || getSolicitacoes().getTitulo().equals("")) {

			Messagebox.show("Titulo em branco ou inválido!", "Error",
					Messagebox.OK, Messagebox.ERROR);
			ret.setValid(false);
		}else  if (getSolicitacoes().getTitulo().length() < 3) {
		  Messagebox.show("Titulo com menos de 3 caracteres!", "Error",
		  Messagebox.OK, Messagebox.ERROR); ret.setValid(false); 
		  
		}else  if (getSolicitacoes().getDescricao()== null || getSolicitacoes().getDescricao().equals("")) {
			Messagebox.show("Descrição em branco ou inválida!", "Error",
					Messagebox.OK, Messagebox.ERROR);
			ret.setValid(false);
		}else  if (getSolicitacoes().getData()== null || getSolicitacoes().getData().equals("")) {
			Messagebox.show("Data em branco ou inválida!", "Error",
					Messagebox.OK, Messagebox.ERROR);
			ret.setValid(false);
					  
		}else if (getSolicitacoes().getHora()== null || getSolicitacoes().getHora().equals("")) {
			Messagebox.show("Hora em branco ou inválida!", "Error",
					Messagebox.OK, Messagebox.ERROR);
			ret.setValid(false);
		}else if (getSolicitacoes().getRecurso()== null || getSolicitacoes().getRecurso().equals("")) {
			Messagebox.show("Recurso em branco ou inválido!", "Error",
					Messagebox.OK, Messagebox.ERROR);
			ret.setValid(false);
		}
		return ret;
	}

	public GerenciarSolicitacoes getEntity(String id) {
		GerenciarSolicitacoes solicitacoes = new GerenciarSolicitacoes();
		solicitacoes.setIdsolicitacoes(Integer.parseInt(id));
		ColecaoGerenciarSolicitacoes listaSolicitacoes = new ColecaoGerenciarSolicitacoes();
		try {
			listaSolicitacoes.setAll(dao.pesquisarID(solicitacoes));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		solicitacoes = ((ColecaoGerenciarSolicitacoes) listaSolicitacoes).getIndice(0);
		return solicitacoes;
	}
	
	@Override
	public Return validarItemUnico(IModel<?> imodel) {
		// TODO Auto-generated method stub
		return null;
	}
	public GerenciarSolicitacoes getSolicitacoes() {
		return solicitacoes;
	}
	public void setSolicitacoes(GerenciarSolicitacoes solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

}
