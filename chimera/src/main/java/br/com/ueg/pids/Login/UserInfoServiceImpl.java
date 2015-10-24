package br.com.ueg.pids.Login;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ueg.pids.Colections.ColecaoUsuario;
import br.com.ueg.pids.DAO.GenericDAO;
import br.com.ueg.pids.Model.IModel;
import br.com.ueg.pids.Model.UserInfoService;
import br.com.ueg.pids.Model.Usuario;

public class UserInfoServiceImpl implements UserInfoService,Serializable{
	private static final long serialVersionUID = 1L;
	protected GenericDAO dao  = new GenericDAO();
	
	static protected List<Usuario> userList = new ArrayList<Usuario>();  

	/**
	 * sincronizado é apenas porque é usado UserList estática para impedir o
	 * acesso simultâneo
	 **/
	public synchronized Usuario findUser(String account) {

		IModel<?> usuario = (IModel<?>) new Usuario();
		ColecaoUsuario colecaoUsuario = new ColecaoUsuario();
		try {
			colecaoUsuario.setAll(dao.pesquisarCriterio(usuario, "account",account));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		userList = colecaoUsuario.getAll();
		int s = userList.size();
		for (int i = 0; i < s; i++) {
			Usuario u = userList.get(i);
			if (account.equals(u.getAccount())) {
				return Usuario.clone(u);
			}
		}
		return null;
	}
	
	/** sincronizado é apenas porque é usado UserList estática para impedir o acesso simultâneo **/
	public synchronized Usuario updateUser(Usuario user){
		IModel<?> usuario = (IModel<?>) new Usuario();
		ColecaoUsuario colecaoUsuario = new ColecaoUsuario();
		try {
			colecaoUsuario.setAll(dao.pesquisarCriterio(usuario, "account", user.getAccount()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		userList = colecaoUsuario.getAll();
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
