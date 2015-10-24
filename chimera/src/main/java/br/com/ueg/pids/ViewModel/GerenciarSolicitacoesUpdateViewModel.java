package br.com.ueg.pids.ViewModel;

import br.com.ueg.pids.Control.GerenciarSolicitacoesController;
import br.com.ueg.pids.Model.GerenciarSolicitacoes;

@SuppressWarnings("serial")
public class GerenciarSolicitacoesUpdateViewModel extends GenericViewModel<GerenciarSolicitacoes, GerenciarSolicitacoesController>{

	@Override
	public GerenciarSolicitacoes getObject() {
		return new GerenciarSolicitacoes();
	}

	@Override
	public GerenciarSolicitacoesController getControl() {
		return new GerenciarSolicitacoesController();
	}

}
