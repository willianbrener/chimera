package br.com.ueg.pids.ViewModel;

import org.zkoss.bind.annotation.Init;

import br.com.ueg.pids.Control.GerenciarSolicitacoesController;
import br.com.ueg.pids.Model.GerenciarSolicitacoes;

public class GerenciarSolicitacoesViewModel extends GenericViewModel<GerenciarSolicitacoes, GerenciarSolicitacoesController>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1458228584510026962L;

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
