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
import br.com.ueg.pids.Control.RecursoController;
import br.com.ueg.pids.Model.Departamento;
import br.com.ueg.pids.Model.Recurso;
import br.com.ueg.pids.Utils.Return;

@SuppressWarnings("serial")
public class RecursoUpdateViewModel extends
		GenericViewModel<Recurso, RecursoController> {

	/**
	 * 
	 */
	
	@Wire("#RecursoUpdate")
	private Window win;
	private String recordMode;
	private Departamento departamentoSelecionado;
	private List<Departamento> departamentoList;
	private List<Recurso> lstRecurso;

	@Init
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("RecursoObject") Recurso c1,
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
	
	@NotifyChange("entity")
	@Command
	public Return update() {
		Return ret = new Return(true);

		if (departamentoSelecionado == null) {
			ret = getControl().alterar(getItemSelected());
		} else {
			getItemSelected().setDepartamento(departamentoSelecionado);
			
			ret = getControl().alterar(getItemSelected());
		}
		
		if (ret.isValid()) {
			closeThis();
			Messagebox.show("Recurso alterado com sucesso!", "Sucess",Messagebox.OK, Messagebox.INFORMATION);
			
			Executions.sendRedirect("/paginas/cadastros_base/recurso/pesquisar.zul");
		}
		return ret;
	}

	public String getRecordMode() {
		return recordMode;
	}

	public void setRecordMode(String recordMode) {
		this.recordMode = recordMode;
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

	public Window getWin() {
		return win;
	}

	public void setWin(Window win) {
		this.win = win;
	}

	public List<Recurso> getLstRecurso() {
		return lstRecurso;
	}

	public void setLstRecurso(List<Recurso> lstRecurso) {
		this.lstRecurso = lstRecurso;
	}

	@Override
	public Recurso getObject() {
		return new Recurso();
	}

	@Override
	public RecursoController getControl() {
		return new RecursoController();
	}
	
}
