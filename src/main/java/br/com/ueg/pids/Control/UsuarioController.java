package br.com.ueg.pids.Control;

import java.sql.SQLException;
import java.util.List;


import br.com.ueg.pids.Colections.ColecaoUsuario;
import br.com.ueg.pids.Enum.TypeMessage;
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
	public Return validar(IModel<?> imodel) throws SQLException {
		setUsuario((Usuario) imodel);
		Return ret = new Return(true);
		Mailer mail = new Mailer();
		
		if (getUsuario().getNome() == null || getUsuario().getNome().equals("")) {

			ret = new Return(false, "Nome em branco ou inválida!",
					TypeMessage.ERROR);
		}else if(getUsuario().getNome().length() < 3){
			ret = new Return(false, "Nome com menos de 3 caracteres!",
					TypeMessage.ERROR);
		}else if(getUsuario().getAccount() == null || getUsuario().getAccount().equals("")){
			ret = new Return(false, "Conta em branco ou inválida!",
					TypeMessage.ERROR);
		}else if(getUsuario().getAccount().length() < 3){
			ret = new Return(false, "Conta com menos de 3 caracteres!",
					TypeMessage.ERROR);
		}else if(getUsuario().getPassword() == null || getUsuario().getPassword().equals("")){
			ret = new Return(false, "Senha em branco ou inválido!",
					TypeMessage.ERROR);
		}else if(!mail.validatorEmail(getUsuario().getEmail())){
			ret = new Return(false, "Email em branco ou inválido!",
					TypeMessage.ERROR);
		}else if(getUsuario().getPermissao() == null || getUsuario().getPermissao().equals("")){
			ret = new Return(false, "Permissao em branco ou inválido!",
					TypeMessage.ERROR);
		}else if(getUsuario().getCargo() == null || getUsuario().getCargo().equals("")){
			ret = new Return(false, "Cargo em branco ou inválido!",
					TypeMessage.ERROR);
		}else if(!dao.pesquisarNome(usuario, getUsuario().getAccount()).isEmpty()){
			ret = new Return(false, "Conta já existente!", TypeMessage.ERROR);
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
