package br.com.ueg.pids.Login;


import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import br.com.ueg.pids.Model.UserCredential;
import br.com.ueg.pids.Model.UserInfoService;
import br.com.ueg.pids.Model.Usuario;

public class AuthentificationServiceImpl2 extends AuthentificationServiceImpl1{

	private static final long serialVersionUID = 1L;
	
	UserInfoService userInfoService = new UserInfoServiceImpl();
	
	@Override
	public boolean login(String nm, String pd) {
		Usuario user = userInfoService.findUser(nm);
		//simples de verificação de senha
		if (user == null || !user.getPassword().equals(pd)) {
			return false;
		}
		
		Session sess = Sessions.getCurrent();
		UserCredential cre = new UserCredential(user.getAccount(),user.getFullName());
		
		if(cre.isAnonymous()){
			return false;
		}
		sess.setAttribute("userCredential",cre);
		
		return true;
	}
	@Override
	public void logout() {
		Session sess = Sessions.getCurrent();
		sess.removeAttribute("userCredential");
	}
}
