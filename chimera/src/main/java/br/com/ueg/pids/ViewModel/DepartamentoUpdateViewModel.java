package br.com.ueg.pids.ViewModel;

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

import br.com.ueg.pids.Control.DepartamentoController;
import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Model.Departamento;
import br.com.ueg.pids.Utils.Return;

public class DepartamentoUpdateViewModel extends GenericViewModel<Departamento, DepartamentoController>{

	@Wire("#DepartamentoUpdate")
	private Window win;
	
	private String recordMode;
	private List<Departamento> lstDepartamento;
	

	@Init
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("DepartamentoObject") Departamento c1,
			@ExecutionArgParam("recordMode") String recordMode)
			throws CloneNotSupportedException {
		Selectors.wireComponents(view, this, false);
		setRecordMode(recordMode);
		if (recordMode.equals("EDIT")) {
			itemSelected = c1;
		}
	}

	@Command
	public void closeThis() {
		win.detach();
	}
	
	@NotifyChange("lstDepartamento")
	@Command
	public Return update() {
		Return ret = new Return(true);

		
			ret = getControl().alterar(getItemSelected());
		
		if (ret.isValid()) {
			closeThis();
			Messagebox.show("Departamento alterado com sucesso!", "Sucess",Messagebox.OK, Messagebox.INFORMATION);
			
		}
		return ret;
	}
	
	
	
	@Override
	public Departamento getObject() {
		// TODO Auto-generated method stub
		return new Departamento();
	}

	@Override
	public DepartamentoController getControl() {
		// TODO Auto-generated method stub
		return new DepartamentoController();
	}

	public Window getWin() {
		return win;
	}

	public String getRecordMode() {
		return recordMode;
	}

	public List<Departamento> getLstDepartamento() {
		return lstDepartamento;
	}

	public void setWin(Window win) {
		this.win = win;
	}

	public void setRecordMode(String recordMode) {
		this.recordMode = recordMode;
	}

	public void setLstDepartamento(List<Departamento> lstDepartamento) {
		this.lstDepartamento = lstDepartamento;
	}

	
	
}
