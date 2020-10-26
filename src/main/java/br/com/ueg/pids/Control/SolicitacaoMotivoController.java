package br.com.ueg.pids.Control;

import br.com.ueg.pids.Model.IModel;
import br.com.ueg.pids.Model.SolicitacaoMotivo;
import br.com.ueg.pids.Utils.Return;

public class SolicitacaoMotivoController extends GenericController<SolicitacaoMotivo>{

	@Override
	public Return validar(IModel<?> imodel) {
		Return ret = new Return(true);
		return ret;
	}

	@Override
	public Return validarItemUnico(IModel<?> imodel) {
		return null;
	}

}
