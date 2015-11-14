package br.com.ueg.pids.Control;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

import br.com.ueg.pids.Login.AuthentificationServiceImpl2;
import br.com.ueg.pids.Model.AuthenticationService;
import br.com.ueg.pids.Model.UserCredential;

public class LoginController extends SelectorComposer<Component> {
private static final long serialVersionUID = 1L;

//wire components
@Wire
Textbox account;
@Wire
Textbox password;
@Wire
Label message;

//services
AuthenticationService authService = new AuthentificationServiceImpl2();
Session sess = Sessions.getCurrent();

@Listen("onClick=#login; onOK=#loginWin")
	public void doLogin(){
		String nm = account.getValue();
		String pd = password.getValue();
		
		
		if (!authService.login(nm, pd)) {
			message.setValue("account ou password incorretos.");
			return;
		}
		UserCredential cre= authService.getUserCredential();
		
		if(cre.isSolicitante()){
			Executions.sendRedirect("/paginas/initial_page_user.zul");
		}else if(cre.isAprovador()){
			Executions.sendRedirect("/paginas/initial_page_approver.zul");
		}else if(cre.isExecutor()){
			Executions.sendRedirect("/paginas/initial_page_executioner.zul");
		}else if(cre.isAdmin()){
			Executions.sendRedirect("/paginas/initial_page_master.zul");
		}
		message.setValue("Bem vindo, " + cre.getName());
		message.setSclass("");
	}
@Listen("onClick=#logout;")
	public void doLogout() {
		sess.removeAttribute("userCredential");
		Executions.sendRedirect("/login.zul");
	}

}
