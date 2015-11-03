package br.com.ueg.pids.Control;

import java.sql.SQLException;
import java.util.List;

import org.zkoss.zul.Messagebox;

import br.com.ueg.pids.Colections.ColecaoUsuario;
import br.com.ueg.pids.Mailer.Mailer;
import br.com.ueg.pids.Model.IModel;
import br.com.ueg.pids.Model.Usuario;
import br.com.ueg.pids.Utils.Return;

public class UsuarioController extends GenericController<Usuario> {
	
	Usuario usuario = new Usuario();
	public List<?> getListarTodos(Usuario usuario) {
		ColecaoUsuario listaUsuario = new ColecaoUsuario();
		try {
			listaUsuario.setAll(dao.listarTodos(usuario));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaUsuario.getAll();
	}
	
	

	@Override
	public Return validar(IModel<?> imodel) {
		setUsuario((Usuario) imodel);
		Return ret = new Return(true);
		Mailer mail = new Mailer();
		
		if (getUsuario().getNome() == null || getUsuario().getNome().equals("")) {

			Messagebox.show("Nome em branco ou inválido!", "Error",
					Messagebox.OK, Messagebox.ERROR);
			ret.setValid(false);
		}else if(getUsuario().getNome().length() < 3){
			Messagebox.show("Nome com menos de 3 caracteres!", "Error",
					Messagebox.OK, Messagebox.ERROR);
			ret.setValid(false);
		}else if(getUsuario().getAccount() == null || getUsuario().getAccount().equals("")){
			Messagebox.show("Conta em branco ou inválida!", "Error",
					Messagebox.OK, Messagebox.ERROR);
			ret.setValid(false);
		}else if(getUsuario().getAccount().length() < 3){
			Messagebox.show("Conta com menos de 3 caracteres!", "Error",
					Messagebox.OK, Messagebox.ERROR);
			ret.setValid(false);
		}else if(getUsuario().getPassword() == null || getUsuario().getPassword().equals("")){
			Messagebox.show("Senha em branco ou inválido!", "Error",
					Messagebox.OK, Messagebox.ERROR);
			ret.setValid(false);
		}else if(!mail.validatorEmail(getUsuario().getEmail())){
			Messagebox.show("Email em branco ou inválido!", "Error",
					Messagebox.OK, Messagebox.ERROR);
			ret.setValid(false);
		}else if(getUsuario().getPermissao() == null || getUsuario().getPermissao().equals("")){
			Messagebox.show("Permissao em branco ou inválido!", "Error",
					Messagebox.OK, Messagebox.ERROR);
			ret.setValid(false);
		}else if(getUsuario().getCargo() == null || getUsuario().getCargo().equals("")){
			Messagebox.show("Cargo em branco ou inválido!", "Error",
					Messagebox.OK, Messagebox.ERROR);
			ret.setValid(false);
		}
			return ret;
	}

	@Override
	public Return validarItemUnico(IModel<?> imodel) {
		setUsuario((Usuario) imodel);
		Return ret = new Return(true);
		return ret;
	}

	public Usuario getEntity(String id) {
		Usuario usuario = new Usuario();
		usuario.setIdusuario(Integer.parseInt(id));
		ColecaoUsuario listaUsuario = new ColecaoUsuario();
		try {
			listaUsuario.setAll(dao.pesquisarID(usuario));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		usuario = ((ColecaoUsuario) listaUsuario).getIndice(0);
		return usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
