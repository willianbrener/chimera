package br.com.ueg.pids.ViewModel;

import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import br.com.ueg.pids.Control.CargoController;
import br.com.ueg.pids.Control.GerenciarSolicitacoesController;
import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Model.GerenciarSolicitacoes;
import br.com.ueg.pids.Model.Recurso;
import br.com.ueg.pids.Utils.Return;

@SuppressWarnings("serial")
public class GerenciarSolicitacoesViewModel extends GenericViewModel<GerenciarSolicitacoes, GerenciarSolicitacoesController>{
/*
* USUARIO = READONLY
* CARGO = DROPDOWN
* AO TRAZER CARGO TRAZER DEPARTAMENTO REFERENTE
* RECURSOS = DROPDOWN
* 
*/
	private List<Cargo> lstCargo;
	private List<Recurso> lstRecurso;
	private Cargo cargoSelected = new Cargo(); 
	@Init
	public void init() {
		super.init();
	}
	
	@Command
	public Return salvar(){
		Return ret = new Return(true);
		
		return ret;
	}
	
	@Override
	public GerenciarSolicitacoes getObject() {
		return new GerenciarSolicitacoes();
	}

	@Override
	public GerenciarSolicitacoesController getControl() {
		return new GerenciarSolicitacoesController();
	}
	
	@NotifyChange("categoriaList")
	public List<Cargo> getCargoList(){
		CargoController cargoController = new CargoController();
		lstCargo = cargoController.getLstEntities();
    	return (List<Cargo>) lstCargo;
    }

	public List<Cargo> getLstCargo() {
		return lstCargo;
	}

	public void setLstCargo(List<Cargo> lstCargo) {
		this.lstCargo = lstCargo;
	}

	public List<Recurso> getLstRecurso() {
		return lstRecurso;
	}

	public void setLstRecurso(List<Recurso> lstRecurso) {
		this.lstRecurso = lstRecurso;
	}

	public Cargo getCargoSelected() {
		return cargoSelected;
	}

	public void setCargoSelected(Cargo cargoSelected) {
		this.cargoSelected = cargoSelected;
	}
	
}
