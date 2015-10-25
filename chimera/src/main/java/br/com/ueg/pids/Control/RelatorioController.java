package br.com.ueg.pids.Control;

import br.com.ueg.pids.Model.IModel;
import br.com.ueg.pids.Model.Relatorio;
import br.com.ueg.pids.Utils.Return;


public class RelatorioController extends GenericController<Relatorio> {

	@Override
	public Return validar(IModel<?> imodel) {
		return null;
	}

	@Override
	public Return validarItemUnico(IModel<?> imodel) {
		return null;
	}

}
