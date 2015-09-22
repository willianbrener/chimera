package br.com.ueg.pids.Control;

import java.sql.SQLException;
import java.util.List;

import org.zkoss.zul.Messagebox;








import br.com.ueg.pids.Utils.MessageBoxx;
import br.com.ueg.pids.Utils.Return;
import br.com.ueg.pids.Colections.ColecaoCargo;
import br.com.ueg.pids.Colections.ColecaoDepartamento;
import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Model.Departamento;
import br.com.ueg.pids.Model.IModel;

public class CargoController extends GenericController<Cargo> {

	private Cargo cargo = new Cargo();
	MessageBoxx msgbox = new MessageBoxx();
	private Cargo cargoSelecionado = new Cargo();
	@Override
	public Return validar(IModel<?> imodel) {
		setCargo((Cargo) imodel);
		Return ret = new Return(true);
		if (getCargo().getNome() == null || getCargo().getNome().equals("")) {

			Messagebox.show("Nome em branco ou inválido!", "Error",
					Messagebox.OK, Messagebox.ERROR);
			ret.setValid(false);
		}else  if (getCargo().getNome().length() < 3) {
		  Messagebox.show("Nome com menos de 3 caracteres!", "Error",
		  Messagebox.OK, Messagebox.ERROR); ret.setValid(false); 
		  
		}else if (getCargo().getDescricao() == null
				|| getCargo().getDescricao().equals("")) {

			Messagebox.show("Descrição em branco!", "Error", Messagebox.OK,
					Messagebox.ERROR);
			ret.setValid(false);
		}else if (getCargo().getDepartamento() == null
				|| getCargo().getDepartamento().equals("")) {
			Messagebox.show("Departamento em branco!", "Error", Messagebox.OK,
					Messagebox.ERROR);
			ret.setValid(false);
		}
		return ret;
	}
	
	@Override
	public List<?> getLstEntities(String keyword) {
		Cargo cargo = new Cargo();
		ColecaoCargo listaCargo = new ColecaoCargo();
		try {
			
				listaCargo.setAll(dao.pesquisarNome(cargo, keyword));
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCargo.getAll();
	}
	
	public Cargo getEntity(String id) {
		Cargo cargo = new Cargo();
		cargo.setIdcargo(Integer.parseInt(id));
		ColecaoCargo listaCargo = new ColecaoCargo();
		try {
			listaCargo.setAll(dao.pesquisarID(cargo));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cargo = ((ColecaoCargo) listaCargo).getIndice(0);
		return cargo;
	}
	
	@Override
	public Return validarItemUnico(IModel<?> imodel) {
		Return ret = new Return(true);
		return ret;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public MessageBoxx getMsgbox() {
		return msgbox;
	}
	public void setMsgbox(MessageBoxx msgbox) {
		this.msgbox = msgbox;
	}



	public Cargo getCargoSelecionado() {
		return cargoSelecionado;
	}



	public void setCargoSelecionado(Cargo cargoSelecionado) {
		this.cargoSelecionado = cargoSelecionado;
	}


	/*@Override
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
	}*/


	

}
