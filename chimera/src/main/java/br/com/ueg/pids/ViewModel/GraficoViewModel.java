package br.com.ueg.pids.ViewModel;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.PieModel;

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
        engine = new MotorGrafico();
 
        model = Grafico.getModel();
    }
 
    public MotorGrafico getEngine() {
        return engine;
    }
 
    public PieModel getModel() {
        return model;
    }
 
    public boolean isThreeD() {
        return threeD;
    }
     
    public String getMessage(){
        return message;
    }
     
    @Command("showMessage") 
    @NotifyChange("message")
    public void onShowMessage(
            @BindingParam("msg") String message){
        this.message = message;
    }
     
    @GlobalCommand("dataChanged") 
    @NotifyChange("model")
    public void onDataChanged(
            @BindingParam("category")String category,
            @BindingParam("num") Number num){
        model.setValue(category, num);
    }
     
    @GlobalCommand("configChanged") 
    @NotifyChange({"threeD","engine"})
    public void onConfigChanged(
            @BindingParam("threeD") boolean threeD,
            @BindingParam("exploded") boolean exploded){
        this.threeD = threeD;
        engine.setExplode(exploded);
    }

	@Override
	public Grafico getObject() {
		return null;
	}

	@Override
	public GraficoController getControl() {
		return null;
	}
}
