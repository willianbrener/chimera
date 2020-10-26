package br.com.ueg.pids.ViewModel.Update;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

import br.com.ueg.pids.Control.GerenciarSolicitacoesController;
import br.com.ueg.pids.Control.RecursoController;
import br.com.ueg.pids.Enum.Permissao;
import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Model.GerenciarSolicitacoes;
import br.com.ueg.pids.Model.Recurso;
import br.com.ueg.pids.Model.Usuario;
import br.com.ueg.pids.Utils.Return;
import br.com.ueg.pids.ViewModel.GenericViewModel;

@SuppressWarnings("serial")
public class GerenciarSolicitacoesUpdateViewModel
		extends
		GenericViewModel<GerenciarSolicitacoes, GerenciarSolicitacoesController> {

	@Wire("#SolicitacoesUpdate")
	private Window win;
	private String recordMode;
	private Cargo cargoSelected = new Cargo();
	private Permissao permissaoSelecionada;
	private List<Permissao> permissaoList = new ArrayList<Permissao>();

	private Usuario usuario = new Usuario();
	private List<?> lstUsuarios;
	private List<Recurso> lstRecurso;
	private Date data = new Date();

	@Init
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("SolicitacaoObject") GerenciarSolicitacoes c1,
			@ExecutionArgParam("recordMode") String recordMode)
			throws CloneNotSupportedException {
		Selectors.wireComponents(view, this, false);
		setRecordMode(recordMode);
		if (recordMode.equals("EDIT")) {
			setEntity(c1);
		}
	}

	@NotifyChange("entity")
	@Command
	public Return update() throws SQLException {
		Return ret = new Return(true);

		ret = getControl().alterar(getItemSelected());

		if (ret.isValid()) {
			closeThis();
			Messagebox.show("Solicita��o alterada com sucesso!", "Sucess",
					Messagebox.OK, Messagebox.INFORMATION);
		}
		return ret;
	}

	@Command
	public void closeThis() {
		win.detach();
	}

	@Override
	public GerenciarSolicitacoes getObject() {
		return new GerenciarSolicitacoes();
	}

	@Override
	public GerenciarSolicitacoesController getControl() {
		return new GerenciarSolicitacoesController();
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

	public Cargo getCargoSelected() {
		return cargoSelected;
	}

	public void setCargoSelected(Cargo cargoSelected) {
		this.cargoSelected = cargoSelected;
	}

	public Permissao getPermissaoSelecionada() {
		return permissaoSelecionada;
	}

	public void setPermissaoSelecionada(Permissao permissaoSelecionada) {
		this.permissaoSelecionada = permissaoSelecionada;
	}

	

	public void setPermissaoList(List<Permissao> permissaoList) {
		this.permissaoList = permissaoList;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<?> getLstUsuarios() {
		return lstUsuarios;
	}

	public void setLstUsuarios(List<?> lstUsuarios) {
		this.lstUsuarios = lstUsuarios;
	}

	@NotifyChange("permissaoList")
	public List<Permissao> getPermissaoList() {
		for (Permissao permissao : Permissao.values()) {
			permissaoList.add(permissao);
		}
		return permissaoList;
	}

	@NotifyChange("lstRecurso")
	public List<Recurso> getRecursoList() {
		RecursoController recursoController = new RecursoController();
		lstRecurso = recursoController.getLstEntities();
		return lstRecurso;
	}


	public void setLstRecurso(List<Recurso> lstRecurso) {
		this.lstRecurso = lstRecurso;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
