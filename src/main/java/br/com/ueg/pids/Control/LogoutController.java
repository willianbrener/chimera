package br.com.ueg.pids.Control;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

import br.com.ueg.pids.Login.AuthentificationServiceImpl2;
import br.com.ueg.pids.Model.AuthenticationService;

@SuppressWarnings("serial")
public class LogoutController extends SelectorComposer<Component>{

AuthenticationService authService = new AuthentificationServiceImpl2();
	
	@Listen("onClick=#logout")
	public void doLogout(){
		authService.logout();		
		Executions.sendRedirect("/login.zul");
	}
	
}
