package br.com.ueg.pids.ViewModel;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.PieModel;
import org.zkoss.zul.SimplePieModel;

import br.com.ueg.pids.Control.GraficoController;
import br.com.ueg.pids.Model.Grafico;
import br.com.ueg.pids.Utils.MotorGrafico;

@SuppressWarnings("serial")
public class GraficoViewModel extends GenericViewModel<Grafico, GraficoController>{

	MotorGrafico engine;
    PieModel model;
    boolean threeD = false;
    String message;
     
    @Init
    public void init() {
    	super.init();
        engine = new MotorGrafico();
        model = getModel();
    }
 
    public MotorGrafico getEngine() {
        return engine;
    }
 
    public boolean isThreeD() {
        return threeD;
    }
     
    public String getMessage(){
        return message;
    }
    
    public PieModel getModel(){
        PieModel model = new SimplePieModel();
        
        
        model.setValue("PENDENTES", new Double(getControl().listaQuantia("situacao", "situacao", "PENDENTE")));
        model.setValue("APROVADAS", new Double(getControl().listaQuantia("situacao", "situacao", "APROVADA")));
        model.setValue("EXECUTADAS", new Double(getControl().listaQuantia("situacao", "situacao", "EXECUTADA")));
        return model;
    }
     
    @Command("showMessage") 
    @NotifyChange("message")
    public void onShowMessage(
            @BindingParam("msg") String message){
        this.message = message;
    }
     
	@Override
	public Grafico getObject() {
		return new Grafico();
	}

	@Override
	public GraficoController getControl() {
		return new GraficoController();
	}
}
