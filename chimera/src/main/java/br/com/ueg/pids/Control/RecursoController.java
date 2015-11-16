package br.com.ueg.pids.Control;

import java.sql.SQLException;
import java.util.List;

import br.com.ueg.pids.Colections.ColecaoRecurso;
import br.com.ueg.pids.Model.IModel;
import br.com.ueg.pids.Model.Recurso;
import br.com.ueg.pids.Utils.Return;

public class RecursoController extends GenericController<Recurso>{
	
	
	@Override
	public List<Recurso> getLstEntities() {
		Recurso recurso = new Recurso();
		ColecaoRecurso listaRecurso = new ColecaoRecurso();
		try {
			
			listaRecurso.setAll(dao.listarTodos(recurso));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaRecurso.getAll();
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

	public Recurso getEntity(String id) {
		Recurso recurso = new Recurso();
		recurso.setIdrecurso(Integer.parseInt(id));
		ColecaoRecurso listaRecurso = new ColecaoRecurso();
		try {
			listaRecurso.setAll(dao.pesquisarID(recurso));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		recurso = ((ColecaoRecurso) listaRecurso).getIndice(0);
		return recurso;
	}
	
	public List<?> getListarTodos(Recurso recurso) {
		ColecaoRecurso listaRecurso = new ColecaoRecurso();
		try {
			
				listaRecurso.setAll(dao.listarTodos(recurso));
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaRecurso.getAll();
	}
}
