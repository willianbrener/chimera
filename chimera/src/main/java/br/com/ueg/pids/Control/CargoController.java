package br.com.ueg.pids.Control;

import java.sql.SQLException;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Messagebox;

import br.com.ueg.pids.Utils.Return;
import br.com.ueg.pids.Colections.ColecaoCargo;
import br.com.ueg.pids.Enum.TypeMessage;
import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Model.IModel;

public class CargoController extends GenericController<Cargo> {

	private Cargo cargo = new Cargo();
	private Cargo cargoSelecionado = new Cargo();

	@Override
	public Return validar(IModel<?> imodel) {

		setCargo((Cargo) imodel);
		Return ret = new Return(true);
		if (getCargo().getNome() == null || getCargo().getNome().equals("")) {

			ret = new Return(false, "Nome em branco ou inválido!",
					TypeMessage.ERROR);

		} else if (getCargo().getNome().length() < 3) {
			ret = new Return(false, "Nome com menos de 3 caracteres!",
					TypeMessage.ERROR);

		} else if (getCargo().getDepartamento() == null
				|| getCargo().getDepartamento().equals("")) {

			ret = new Return(false, "Departamento em branco!",
					TypeMessage.ERROR);

		} else if (getCargo().getDescricao() == null
				|| getCargo().getDescricao().equals("")) {
			ret = new Return(false, "Descrição em branco!", TypeMessage.ERROR);

		}

		return ret;
	}

	public List<?> getListarTodos(Cargo cargo) {
		ColecaoCargo listaCargo = new ColecaoCargo();
		try {

			listaCargo.setAll(dao.listarTodos(cargo));

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

	public Cargo getCargoSelecionado() {
		return cargoSelecionado;
	}

	public void setCargoSelecionado(Cargo cargoSelecionado) {
		this.cargoSelecionado = cargoSelecionado;
	}

}
