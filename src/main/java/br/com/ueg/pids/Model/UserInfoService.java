package br.com.ueg.pids.Model;



public interface UserInfoService {

	/** encontra usuario pela conta **/
	public Usuario findUser(String account);
	
	/** atualiza usuario **/
	public Usuario updateUser(Usuario user);
}
