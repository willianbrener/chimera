package br.com.ueg.pids.ViewModel.Update;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import br.com.ueg.pids.Control.CargoController;
import br.com.ueg.pids.Control.UsuarioController;
import br.com.ueg.pids.Enum.Permissao;
import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Model.Usuario;
import br.com.ueg.pids.Utils.Return;
import br.com.ueg.pids.ViewModel.GenericViewModel;

@SuppressWarnings("serial")
public class UsuarioUpdateViewModel extends GenericViewModel<Usuario, UsuarioController>{
	
	@Wire("#UsuarioUpdate")
	private Window win;
	private String recordMode;
	private List<Usuario> lstUsuario;
	private List<?> cargoList;
	private List<Permissao> permissaoList = new ArrayList<Permissao>();
	private Permissao permissaoSelecionada;
	
	@Init
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("UsuarioObject") Usuario c1,
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
	
	@NotifyChange("lstUsuario")
	@Command
	public Return update() throws SQLException {
		Return ret = new Return(true);
			ret = getControl().alterar(getEntity());
		
		
		if (ret.isValid()) {
			closeThis();
			Messagebox.show("Usuario alterado com sucesso!", "Sucess",Messagebox.OK, Messagebox.INFORMATION);
			
		}
		return ret;
	}
	
	@NotifyChange("cargoList")
	public List<?> getCargoList() {
		Cargo cargo = new Cargo();
		CargoController cargoController = new CargoController();
		cargoList = cargoController.getListarTodos(cargo);
		return cargoList;
	}
	
	@NotifyChange("permissaoList")
	public List<Permissao> getPermissaoList() {
		for (Permissao permissao : Permissao.values()) {
			permissaoList.add(permissao);
		}
		return permissaoList;
	}


	@Override
	public Usuario getObject() {
		return new Usuario();
	}

	@Override
	public UsuarioController getControl() {
		return new UsuarioController();
	}

	public Window getWin() {
		return win;
	}

	public void setWin(Window win) {
		this.win = win;
	}

	public String getRecordMode() {
		return recordMode;
	}

	public void setRecordMode(String recordMode) {
		this.recordMode = recordMode;
	}

	public List<Usuario> getLstUsuario() {
		return lstUsuario;
	}

	public void setLstUsuario(List<Usuario> lstUsuario) {
		this.lstUsuario = lstUsuario;
	}

	public void setCargoList(List<?> cargoList) {
		this.cargoList = cargoList;
	}

	public void setPermissaoList(List<Permissao> permissaoList) {
		this.permissaoList = permissaoList;
	}

	public Permissao getPermissaoSelecionada() {
		return permissaoSelecionada;
	}

	public void setPermissaoSelecionada(Permissao permissaoSelecionada) {
		this.permissaoSelecionada = permissaoSelecionada;
	}
	
	
	
}
