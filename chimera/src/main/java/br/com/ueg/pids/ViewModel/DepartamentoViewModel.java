package br.com.ueg.pids.ViewModel;

import java.sql.SQLException;
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
import br.com.ueg.pids.Enum.TypeMessage;
import br.com.ueg.pids.Model.Departamento;
import br.com.ueg.pids.Utils.Return;

@SuppressWarnings("serial")
public class DepartamentoViewModel extends GenericViewModel<Departamento, DepartamentoController> {
	
	@Wire("#CustomerCRUD")
	private Window win;
	
	private List <?> lstDepartamento;
	private String busca;
	private Integer departamentoSelectedIndex;
	
	@Init
	public void init() {
		super.init();
	}

	@Override
	public Departamento getObject() {
		return new Departamento();
	}

	@Override
	public DepartamentoController getControl() {
		return new DepartamentoController();
	}
	
	@Command
	public Return salvar() throws SQLException {
		Return ret = new Return(true);
					getEntity().setAtivo(true);
					ret = getControl().salvar(getEntity());
			if (ret.isValid()) {
				msgbox.mensagem(TypeMessage.SUCESSO, "Cadastro realizado com sucesso!");
				Executions.sendRedirect("/paginas/cadastros_base/departamento/pesquisar.zul");
			}else{
				msgbox.mensagem(ret.getTypeMessage(), ret.getMensagem());
			}
			return ret;		
	}
	
	@Command
	@NotifyChange("lstDepartamento")
	public void ListarTodos() {
	
		if (getBusca() == null || getBusca().equals("") || getBusca() == "") {
			setLstDepartamento(getControl().listarTodos(getObject()));
		} else {
			setLstDepartamento(getControl().getLstEntities(busca));
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange("lstDepartamento")
	public Return deletar() {

		Return ret = new Return(true);
		if (itemSelected == null) {
			msgbox.mensagem(TypeMessage.AVISO, "Selecione um item para ser deletado!");
		} else {
			String str = "Deseja deletar o departamento \""
					+ getItemSelected().getNome() + "\"?";
			Messagebox.show(str, "Confirm", Messagebox.YES | Messagebox.NO,
					Messagebox.QUESTION, new EventListener() {

						public void onEvent(Event event) throws Exception {
							if (event.getName().equals("onNo")) {
								return;
							} else {
								if (event.getName().equals("onYes")) {

									getControl().desativar(getItemSelected());
									msgbox.mensagem(TypeMessage.SUCESSO, "Departamento deletado com sucesso!");
									setItemSelected(null);
								}
							}
						}
					});

		}
		return ret;
	}
	
	@Command
	public Return telaAlterar() {
		Return ret = new Return(true);
		if (itemSelected == null) {
			msgbox.mensagem(TypeMessage.AVISO, "Selecione algum item para alterar!");
		} else {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("DepartamentoObject", this.itemSelected);
			map.put("recordMode", "EDIT");
			setDepartamentoSelectedIndex(lstDepartamento.indexOf(itemSelected));
			Executions.createComponents("departamento_component.zul", null, map);
			// setItemSelected(null);
		}
		return ret;
	}
	
	

	public List<?> getLstDepartamento() {
		return lstDepartamento;
	}

	public void setLstDepartamento(List<?> lstDepartamento) {
		this.lstDepartamento = lstDepartamento;
	}

	
	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public Integer getDepartamentoSelectedIndex() {
		return departamentoSelectedIndex;
	}

	public void setDepartamentoSelectedIndex(Integer departamentoSelectedIndex) {
		this.departamentoSelectedIndex = departamentoSelectedIndex;
	}

	public Window getWin() {
		return win;
	}

	public void setWin(Window win) {
		this.win = win;
	}
}



	