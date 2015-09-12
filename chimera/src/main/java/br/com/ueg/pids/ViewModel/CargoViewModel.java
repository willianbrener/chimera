package br.com.ueg.pids.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import br.com.ueg.pids.Control.CargoController;
import br.com.ueg.pids.Enum.Departamento;
import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Utils.Return;

public class CargoViewModel extends GenericViewModel<Cargo, CargoController> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5364229695993402589L;
	
	
	@Wire("#CustomerCRUD")
	private Window win;
	private List<Departamento> departamentoList = new ArrayList<Departamento>();
	private Departamento departamentoSelecionado;
	private List<Cargo> lstCargo;
	String aux;
	private Integer cargoSelectedIndex;
	private String busca;

	@Init
	public void init() {
		super.init();
	}

	@SuppressWarnings({})
	@Command
	public Return salvar() {
		Return ret = new Return(true);

		if (getItemSelected() != null) {

			ret = getControl().alterar(getItemSelected());
		} else {

			if (departamentoSelecionado == null) {
				getEntity().setDepartamento(null);
			} else {
				getEntity().setDepartamento(
						getDepartamentoSelecionado().getDescricao());
			}

			ret = getControl().salvar(getEntity());
			if (ret.isValid()) {
				Messagebox.show("Cadastro realizado com sucesso!", "Sucess",
						Messagebox.OK, Messagebox.INFORMATION);
				Executions
						.sendRedirect("/paginas/cadastros_base/cargo/psqCargo.zul");
			}

		}

		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange("lstCargo")
	public Return deletar() {

		Return ret = new Return(true);
		if (itemSelected == null) {
			Messagebox.show("Selecione um item para ser deletado!", "Error",
					Messagebox.OK, Messagebox.EXCLAMATION);
		} else {
			String str = "Deseja deletar o cargo \""
					+ getItemSelected().getName() + "\"?";
			Messagebox.show(str, "Confirm", Messagebox.YES | Messagebox.NO,
					Messagebox.QUESTION, new EventListener() {

						public void onEvent(Event event) throws Exception {
							if (event.getName().equals("onNo")) {
								return;
							} else {
								if (event.getName().equals("onYes")) {

//									getControl().desativar((getItemSelected();
									Messagebox.show(
											"Cargo deletado com sucesso!",
											"Sucess", Messagebox.OK,
											Messagebox.INFORMATION);
									setItemSelected(null);
									// AnnotateDataBinder binder =
									// (AnnotateDataBinder)
									// Page.getAttribute("binder");
									// binder.loadAll();

//									setLstCargo(getControl().ListarTodos());
								}
							}
						}
					});

		}
		return ret;
	}

	@Command
	@NotifyChange("lstCargo")
	public void ListarTodos() {
		if (getBusca() == null || getBusca().equals("") || getBusca() == "") {
			setLstCargo(getControl().getLstEntities());
		} else {
//			setLstCargo(getControl().getLstCriteria((getBusca());
		}

	}

	@Command
	public Return telaAlterar() {
		Return ret = new Return(true);
		if (itemSelected == null) {
			Messagebox.show("Selecione algum item para alterar!", "Error",
					Messagebox.OK, Messagebox.EXCLAMATION);
		} else {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("CargoObject", this.itemSelected);
			map.put("recordMode", "EDIT");
			setCargoSelectedIndex(lstCargo.indexOf(itemSelected));
			Executions.createComponents("cargo_component.zul", null, map);
			// setItemSelected(null);
		}
		return ret;
	}

	@Command
	public void cancel() {
		Executions.sendRedirect("/paginas/initial_page.zul");
	}

	public List<Cargo> getLstCargo() {
		return lstCargo;
	}

	public void setLstCargo(List<Cargo> lstCargo) {
		this.lstCargo = lstCargo;
	}

	public String getAux() {
		return aux;
	}

	public void setAux(String aux) {
		this.aux = aux;
	}

	public Cargo getObject() {
		return new Cargo();
	}

	public CargoController getControl() {
		return new CargoController();
	}

	@NotifyChange("departamentoList")
	public List<Departamento> getDepartamentoList() {
		for (Departamento depart : Departamento.values()) {
			departamentoList.add(depart);
		}
		return departamentoList;
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

	public Integer getCargoSelectedIndex() {
		return cargoSelectedIndex;
	}

	public void setCargoSelectedIndex(Integer cargoSelectedIndex) {
		this.cargoSelectedIndex = cargoSelectedIndex;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

}
