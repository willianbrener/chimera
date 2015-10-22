package br.com.ueg.pids.Control;

import java.sql.SQLException;
import java.util.List;

import br.com.ueg.pids.Colections.ColecaoCargo;
import br.com.ueg.pids.Colections.ColecaoDepartamento;
import br.com.ueg.pids.Colections.ColecaoUsuario;
import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Model.Departamento;
import br.com.ueg.pids.Model.IModel;
import br.com.ueg.pids.Model.Usuario;
import br.com.ueg.pids.Utils.Return;

public class UsuarioController extends GenericController<Usuario> {

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
		return null;
	}

	@Override
	public Return validarItemUnico(IModel<?> imodel) {
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
