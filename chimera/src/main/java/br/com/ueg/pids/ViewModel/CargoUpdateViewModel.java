package br.com.ueg.pids.ViewModel;

import java.util.ArrayList;
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
import br.com.ueg.pids.Enum.Departamento;
import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Utils.Return;

@SuppressWarnings("serial")
public class CargoUpdateViewModel extends
		GenericViewModel<Cargo, CargoController> {

	/**
	 * 
	 */
	
	@Wire("#CargoUpdate")
	private Window win;
	private String recordMode;
	private Departamento departamentoSelecionado;
	private List<Departamento> departamentoList = new ArrayList<Departamento>();
	private List<Cargo> lstCargo;

	@Init
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("CargoObject") Cargo c1,
			@ExecutionArgParam("recordMode") String recordMode)
			throws CloneNotSupportedException {
		Selectors.wireComponents(view, this, false);
		setRecordMode(recordMode);
		if (recordMode.equals("EDIT")) {
//			this.itemSelected = (Cargo) c1.clone();
		}
	}

	@Command
	public void closeThis() {
		win.detach();
	}

	@Command
	public Return update() {
		Return ret = new Return(true);

		if (departamentoSelecionado == null) {
			ret = getControl().alterar(getItemSelected());
		} else {
			getItemSelected().setDepartamento(
					getDepartamentoSelecionado().getDescricao());
			
			ret = getControl().alterar(getItemSelected());
		}
		
		if (ret.isValid()) {
			closeThis();
			Messagebox.show("Cargo alterado com sucesso!", "Sucess",Messagebox.OK, Messagebox.INFORMATION);
			
			Executions.sendRedirect("/paginas/cadastros_base/cargo/psqCargo.zul");
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

	@NotifyChange("departamentoList")
	public List<Departamento> getDepartamentoList() {
		for (Departamento depart : Departamento.values()) {
			departamentoList.add(depart);
		}
		return departamentoList;
	}

	public void setDepartamentoList(List<Departamento> departamentoList) {
		this.departamentoList = departamentoList;
	}

	public List<Cargo> getLstCargo() {
		return lstCargo;
	}

	public void setLstCargo(List<Cargo> lstCargo) {
		this.lstCargo = lstCargo;
	}

}
