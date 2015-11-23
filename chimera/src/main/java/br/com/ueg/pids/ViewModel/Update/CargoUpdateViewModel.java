package br.com.ueg.pids.ViewModel.Update;

import java.sql.SQLException;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import br.com.ueg.pids.Control.CargoController;
import br.com.ueg.pids.Control.DepartamentoController;
import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Model.Departamento;
import br.com.ueg.pids.Utils.Return;
import br.com.ueg.pids.ViewModel.GenericViewModel;

@SuppressWarnings("serial")
public class CargoUpdateViewModel extends
		GenericViewModel<Cargo, CargoController> {

	@Wire("#CargoUpdate")
	private Window win;
	private String recordMode;
	private Departamento departamentoSelecionado;
	private List<Departamento> departamentoList;
	private List<Cargo> lstCargo;

	@Init
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("CargoObject") Cargo c1,
			@ExecutionArgParam("recordMode") String recordMode)
			throws CloneNotSupportedException {
		Selectors.wireComponents(view, this, false);
		setRecordMode(recordMode);
		if (recordMode.equals("EDIT")) {
			setEntity(c1);
		}
	}

	@Command
	public void closeThis() {
		win.detach();
	}

	@NotifyChange("entity")
	@Command
	public Return update() throws SQLException {
		Return ret = new Return(true);
		ret = getControl().alterar(getEntity());

		if (ret.isValid()) {
			closeThis();
			Messagebox.show("Cargo alterado com sucesso!", "Sucess",
					Messagebox.OK, Messagebox.INFORMATION);

			Executions
					.sendRedirect("/paginas/cadastros_base/cargo/pesquisar.zul");
		}
		return ret;
	}

	public String getRecordMode() {
		return recordMode;
	}

	public void setRecordMode(String recordMode) {
		this.recordMode = recordMode;
	}

	@Override
	public Cargo getObject() {
		return new Cargo();
	}

	@Override
	public CargoController getControl() {
		return new CargoController();
	}

	public Departamento getDepartamentoSelecionado() {
		return departamentoSelecionado;
	}

	public void setDepartamentoSelecionado(Departamento departamentoSelecionado) {
		this.departamentoSelecionado = departamentoSelecionado;
	}

	public void setDepartamentoList(List<Departamento> departamentoList) {
		this.departamentoList = departamentoList;
	}

	@NotifyChange("departamentoList")
	public List<Departamento> getDepartamentoList() {
		DepartamentoController departamentoController = new DepartamentoController();
		departamentoList = departamentoController.getLstEntities();
		return departamentoList;
	}

	public List<Cargo> getLstCargo() {
		return lstCargo;
	}

	public void setLstCargo(List<Cargo> lstCargo) {
		this.lstCargo = lstCargo;
	}

}
