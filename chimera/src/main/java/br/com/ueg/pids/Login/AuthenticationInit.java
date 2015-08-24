package br.com.ueg.pids.Login;

import java.util.Map;




import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

import br.com.ueg.pids.Model.AuthenticationService;
import br.com.ueg.pids.Model.UserCredential;

public class AuthenticationInit implements Initiator {

	//services
	AuthenticationService authService = new AuthentificationServiceImpl2();	
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		
		UserCredential cre = authService.getUserCredential();
		if (cre == null || cre.isAnonymous()) {
			Executions.sendRedirect("/login.zul");
			return;
		}
	}
}