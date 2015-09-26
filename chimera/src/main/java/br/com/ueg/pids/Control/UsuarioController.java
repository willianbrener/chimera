package br.com.ueg.pids.Control;

import java.sql.SQLException;

import br.com.ueg.pids.Colections.ColecaoDepartamento;
import br.com.ueg.pids.Colections.ColecaoUsuario;
import br.com.ueg.pids.Model.Departamento;
import br.com.ueg.pids.Model.IModel;
import br.com.ueg.pids.Model.Usuario;
import br.com.ueg.pids.Utils.Return;

public class UsuarioController extends GenericController<Usuario> {

	@Override
	public Return validar(IModel<?> imodel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Return validarItemUnico(IModel<?> imodel) {
		// TODO Auto-generated method stub
		return null;
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

}
