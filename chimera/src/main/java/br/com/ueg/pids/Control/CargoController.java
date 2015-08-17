package br.com.ueg.pids.Control;

import org.zkoss.zul.Messagebox;


import br.com.ueg.pids.Utils.MessageBoxx;
import br.com.ueg.pids.Utils.Return;
import br.com.ueg.pids.Model.Cargo;

public class CargoController extends GenericController<Cargo> {

	
	MessageBoxx msgbox = new MessageBoxx();


	@Override
	public Return validar(Cargo cargo) {
		Return ret = new Return(true);

		if (cargo.getName() == null || cargo.getName().equals("")) {

			Messagebox.show("Nome em branco ou inválido!", "Error",
					Messagebox.OK, Messagebox.ERROR);
			ret.setValid(false);
		}else  if (cargo.getName().length() < 3) {
		  Messagebox.show("Nome com menos de 3 caracteres!", "Error",
		  Messagebox.OK, Messagebox.ERROR); ret.setValid(false); 
		  
		}else if (cargo.getDescricao() == null
				|| cargo.getDescricao().equals("")) {

			Messagebox.show("Descrição em branco!", "Error", Messagebox.OK,
					Messagebox.ERROR);
			ret.setValid(false);
		}else if (cargo.getDepartamento() == null
				|| cargo.getDepartamento().equals("")) {
			Messagebox.show("Departamento em branco!", "Error", Messagebox.OK,
					Messagebox.ERROR);
			ret.setValid(false);
		}
		return ret;
	}

}
