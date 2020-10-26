package br.com.ueg.pids.Login;

import java.io.Serializable;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import br.com.ueg.pids.Model.AuthenticationService;
import br.com.ueg.pids.Model.UserCredential;

@SuppressWarnings("serial")
public class AuthentificationServiceImpl1 implements AuthenticationService,Serializable{

	public UserCredential getUserCredential(){
		Session sess = Sessions.getCurrent();
		UserCredential cre = (UserCredential)sess.getAttribute("userCredential");
		if (cre == null) {
			cre = new UserCredential();//novo usuário anônimo e define a sessão
			sess.setAttribute("userCredential",cre);
		}
		return cre;
	}
	
	public boolean login(String nm, String pd) {
		return false;
	}

	public void logout() {
	}
}
