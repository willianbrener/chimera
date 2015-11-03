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
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import br.com.ueg.pids.Control.DepartamentoController;
import br.com.ueg.pids.Control.MotivoController;
import br.com.ueg.pids.Converter.DateUtils;
import br.com.ueg.pids.Model.Departamento;
import br.com.ueg.pids.Model.GerenciarSolicitacoes;
import br.com.ueg.pids.Model.Motivo;
import br.com.ueg.pids.Utils.Return;

@SuppressWarnings("serial")
public class MotivoViewModel extends
		GenericViewModel<Motivo, MotivoController> {

	@Wire("#Motivo")
	private Window win;
	private String recordMode;
	private DateUtils dateUtils = new DateUtils();
	private List<Motivo> motivoList = new ArrayList<Motivo>();

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("SolicitacaoObject") GerenciarSolicitacoes c1,
			@ExecutionArgParam("recordMode") String recordMode)
			throws CloneNotSupportedException {
		super.init();
		if(recordMode != null){
			Selectors.wireComponents(view, this, false);
			setRecordMode(recordMode);
			if (recordMode.equals("EDIT")) {
				getEntity().setSolicitacao(c1);
			}
		}else{
			getMotivoList();
		}
		
		
		
	}
	
	@Command
	public void closeThis() {
		win.detach();
	}
	
	@Command
	public Return salvar(){
		Return ret = new Return(true);
		getEntity().setAtivo(true);
		
			ret = getControl().salvar(getEntity());
			if(ret.isValid()){
				closeThis();
			}
		return ret;
	}
	
	@NotifyChange("departamentoList")
	public List<Motivo> getMotivoList() {
		
		motivoList = getControl().getLstEntities(user.getName());
		return motivoList;
	}
	
	public DateUtils getDateUtils() {
		return dateUtils;
	}

	public void setDateUtils(DateUtils dateUtils) {
		this.dateUtils = dateUtils;
	}

	public void setMotivoList(List<Motivo> motivoList) {
		this.motivoList = motivoList;
	}

	public String getRecordMode() {
		return recordMode;
	}

	public void setRecordMode(String recordMode) {
		this.recordMode = recordMode;
	}

	public Window getWin() {
		return win;
	}

	public void setWin(Window win) {
		this.win = win;
	}

	@Override
	public Motivo getObject() {
		return new Motivo();
	}

	@Override
	public MotivoController getControl() {
		return new MotivoController();
	}

}
