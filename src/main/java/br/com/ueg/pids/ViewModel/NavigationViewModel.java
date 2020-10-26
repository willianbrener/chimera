package br.com.ueg.pids.ViewModel;


import java.util.Map;


import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Listen;
import br.com.ueg.pids.Model.NavigationPage;




@SuppressWarnings("unused")
public class NavigationViewModel {
	NavigationPage currentPage;
	
	private Map<String, Map<String, NavigationPage>> pageMap;
	
	@Init
	public void init() {
		
		
	}
	@Listen("onSelect = cadastroCargo")
	public void select() {
	    Executions.sendRedirect("/paginas/cadastros_base/cargo/frmCargo.zul");
	}
	
}
