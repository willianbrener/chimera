package br.com.ueg.pids.Login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.ueg.pids.Model.UserInfoService;
import br.com.ueg.pids.Model.Usuario;

public class UserInfoServiceImpl implements UserInfoService,Serializable{
	private static final long serialVersionUID = 1L;
	
	static protected List<Usuario> userList = new ArrayList<Usuario>();  
	static{
		userList.add(new Usuario("anonymous","1234","Anonymous","anonumous@your.com"));
		userList.add(new Usuario("admin","1234","Admin","admin@your.com"));
		userList.add(new Usuario("user","1234","User","user@your.com"));
		userList.add(new Usuario("executioner","1234","Executioner","executioner@your.com"));
	}
	
	/** sincronizado é apenas porque nós usamos UserList estática para impedir o acesso simultâneo **/
	public synchronized Usuario findUser(String account){
		int s = userList.size();
		for(int i=0;i<s;i++){
			Usuario u = userList.get(i);
			if(account.equals(u.getAccount())){
				return Usuario.clone(u);
			}
		}
		return null;
	}
	
	/** sincronizado é apenas porque nós usamos UserList estática para impedir o acesso simultâneo **/
	public synchronized Usuario updateUser(Usuario user){
		int s = userList.size();
		for(int i=0;i<s;i++){
			Usuario u = userList.get(i);
			if(user.getAccount().equals(u.getAccount())){
				userList.set(i,u = Usuario.clone(user));
				return u;
			}
		}
		throw new RuntimeException("user not found "+user.getAccount());
	}
}
