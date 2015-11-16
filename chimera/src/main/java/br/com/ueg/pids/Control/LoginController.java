package br.com.ueg.pids.Control;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

import br.com.ueg.pids.Login.AuthentificationServiceImpl2;
import br.com.ueg.pids.Model.AuthenticationService;
import br.com.ueg.pids.Model.UserCredential;

public class LoginController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	private String password;
	private String account;

	// services
	AuthenticationService authService = new AuthentificationServiceImpl2();
	Session sess = Sessions.getCurrent();
	@NotifyChange("message")
	@Command
	public void doLogin() throws InterruptedException {


		if (!authService.login(account, password)) {
			setMessage("Usuário ou Senha incorretos.");
			return;
		}
		UserCredential cre = authService.getUserCredential();

		if (cre.isSolicitante()) {
			Executions.sendRedirect("/paginas/initial_page_user.zul");
		} else if (cre.isAprovador()) {
			Executions.sendRedirect("/paginas/initial_page_approver.zul");
		} else if (cre.isExecutor()) {
			Executions.sendRedirect("/paginas/initial_page_executioner.zul");
		} else if (cre.isAdmin()) {
			Executions.sendRedirect("/paginas/initial_page_master.zul");
		}
		return;
	}

	@Listen("onClick=#logout;")
	public void doLogout() {
		sess.removeAttribute("userCredential");
		Executions.sendRedirect("/login.zul");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
