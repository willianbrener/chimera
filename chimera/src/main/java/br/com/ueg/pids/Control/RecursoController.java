package br.com.ueg.pids.Control;

import java.sql.SQLException;
import java.util.List;

import br.com.ueg.pids.Colections.ColecaoDepartamento;
import br.com.ueg.pids.Colections.ColecaoRecurso;
import br.com.ueg.pids.Model.Departamento;
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
		return null;
	}

	@Override
	public Return validarItemUnico(IModel<?> imodel) {
		return null;
	}

}
