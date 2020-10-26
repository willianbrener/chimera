package br.com.ueg.pids.Control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ueg.pids.Colections.ColecaoDepartamento;
import br.com.ueg.pids.Colections.ColecaoMotivo;
import br.com.ueg.pids.Colections.ColecaoUsuario;
import br.com.ueg.pids.Model.Departamento;
import br.com.ueg.pids.Model.IModel;
import br.com.ueg.pids.Model.Motivo;
import br.com.ueg.pids.Model.Usuario;
import br.com.ueg.pids.Utils.Return;

public class MotivoController extends GenericController<Motivo> {

	
	
	public Motivo getEntity(String id) {
		Motivo motivo = new Motivo();
		motivo.setIdmotivo(Integer.parseInt(id));
		ColecaoMotivo listaMotivo = new ColecaoMotivo();
		try {
			listaMotivo.setAll(dao.pesquisarID(motivo));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		motivo = ((ColecaoMotivo) listaMotivo).getIndice(0);
		return motivo;
	}
	
	@Override
	public List<Motivo> getLstEntities(String usuarioNome) {
		Motivo motivo = new Motivo();
		ColecaoMotivo listaMotivo = new ColecaoMotivo();
		List<?> listaUsuario;
		List<Usuario> usuario = new ArrayList<Usuario>();
		GerenciarSolicitacoesController usuarioController = new GerenciarSolicitacoesController();
		try {
			listaUsuario = usuarioController.getLstUsuarioDados(usuarioNome);
			usuario = (List<Usuario>) listaUsuario;
			listaMotivo.setAll(dao.pesquisarListaMotivo(motivo, /*usuario.get(0).getIdusuario()*/99999));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaMotivo.getAll();
	}
	
	
	@Override
	public Return validar(IModel<?> imodel) {
		Return ret = new Return(true);
		return ret;
	}

	@Override
	public Return validarItemUnico(IModel<?> imodel) {
		return null;
	}

}
