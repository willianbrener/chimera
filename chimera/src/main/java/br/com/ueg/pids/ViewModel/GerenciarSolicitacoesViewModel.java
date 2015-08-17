package br.com.ueg.pids.ViewModel;

import org.zkoss.bind.annotation.Init;

import br.com.ueg.pids.Control.GerenciarSolicitacoesController;
import br.com.ueg.pids.Model.GerenciarSolicitacoes;

public class GerenciarSolicitacoesViewModel extends GenericViewModel<GerenciarSolicitacoes, GerenciarSolicitacoesController>{


	
	
	@Init
	public void init() {
		super.init();
	}
	
	@Override
	public GerenciarSolicitacoes getObject() {
		return null;
	}

	@Override
	public GerenciarSolicitacoesController getControl() {
		return null;
	}

}
