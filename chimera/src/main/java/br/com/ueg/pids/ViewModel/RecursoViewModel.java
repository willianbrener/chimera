package br.com.ueg.pids.ViewModel;

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

import br.com.ueg.pids.Control.DepartamentoController;
import br.com.ueg.pids.Control.RecursoController;
import br.com.ueg.pids.Enum.TypeMessage;
import br.com.ueg.pids.Model.Departamento;
import br.com.ueg.pids.Model.Recurso;
import br.com.ueg.pids.Utils.Return;

@SuppressWarnings("serial")
public class RecursoViewModel extends GenericViewModel<Recurso, RecursoController> {

	@Wire("#CustomerCRUD")
	private Window win;
	private List<Departamento> departamentoList;
	private Departamento departamentoSelecionado;
	private List<?> lstRecurso;
	String aux;
	private Integer recursoSelectedIndex;
	private String busca;

	@Init
	public void init() {
		super.init();
	}

	@NotifyChange("entity")
	@Command
	public Return salvar() {
		Return ret = new Return(true);
					getEntity().setAtivo(true);
					ret = getControl().salvar(getEntity());
			if (ret.isValid()) {
				msgbox.mensagem(TypeMessage.SUCESSO, "Cadastro realizado com sucesso!");
				Executions
						.sendRedirect("/paginas/cadastros_base/recurso/pesquisar.zul");
		
		}else{
			msgbox.mensagem(ret.getTypeMessage(), ret.getMensagem());
		}

		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange("lstRecurso")
	public Return deletar() {

		Return ret = new Return(true);
		if (itemSelected == null) {
			msgbox.mensagem(TypeMessage.AVISO, "Selecione um item para ser deletado!");
		} else {
			String str = "Deseja deletar o recurso \""
					+ getItemSelected().getNome() + "\"?";
			Messagebox.show(str, "Confirm", Messagebox.YES | Messagebox.NO,
					Messagebox.QUESTION, new EventListener() {

						public void onEvent(Event event) throws Exception {
							if (event.getName().equals("onNo")) {
								return;
							} else {
								if (event.getName().equals("onYes")) {

									getControl().desativar(getItemSelected());
									msgbox.mensagem(TypeMessage.SUCESSO, "Recurso deletado com sucesso!");
									setItemSelected(null);
								}
							}
						}
					});

		}
		return ret;
	}

	@Command
	@NotifyChange("lstRecurso")
	public void ListarTodos() {
		if (getBusca() == null || getBusca().equals("") || getBusca() == "") {
			setLstRecurso(getControl().getListarTodos(getObject()));
		} else {
			setLstRecurso(getControl().getLstEntities(busca));
		}

	}


	@Command
	public Return telaAlterar() {
		Return ret = new Return(true);
		if (itemSelected == null) {
			msgbox.mensagem(TypeMessage.AVISO, "Selecione algum item para alterar!");
		} else {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("RecursoObject", this.itemSelected);
			map.put("recordMode", "EDIT");
			setRecursoSelectedIndex(lstRecurso.indexOf(itemSelected));
			Executions.createComponents("recurso_component.zul", null, map);
		}
		return ret;
	}

	@Command
	public void cancel() {
		Executions.sendRedirect("/paginas/initial_page.zul");
	}
	

	public String getAux() {
		return aux;
	}

	public void setAux(String aux) {
		this.aux = aux;
	}

	@NotifyChange("departamentoList")
	public List<Departamento> getDepartamentoList() {
		DepartamentoController departamentoController = new DepartamentoController();
		departamentoList = departamentoController.getLstEntities();
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

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public Window getWin() {
		return win;
	}

	public void setWin(Window win) {
		this.win = win;
	}

	@Override
	public Recurso getObject() {
		return new Recurso();
	}

	@Override
	public RecursoController getControl() {
		return new RecursoController();
	}

	public List<?> getLstRecurso() {
		return lstRecurso;
	}

	public void setLstRecurso(List<?> lstRecurso) {
		this.lstRecurso = lstRecurso;
	}

	public Integer getRecursoSelectedIndex() {
		return recursoSelectedIndex;
	}

	public void setRecursoSelectedIndex(Integer recursoSelectedIndex) {
		this.recursoSelectedIndex = recursoSelectedIndex;
	}
	
}
