package br.com.ueg.pids.Control;

import java.sql.SQLException;
import java.util.List;

import br.com.ueg.pids.Colections.ColecaoCargo;
import br.com.ueg.pids.Colections.ColecaoDepartamento;
import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Model.Departamento;
import br.com.ueg.pids.Model.IModel;
import br.com.ueg.pids.Utils.Return;

public class DepartamentoController extends GenericController<Departamento>{

	
	
	@Override
	public List<Departamento> getLstEntities() {
		Departamento departamento = new Departamento();
		ColecaoDepartamento listaDepartamento = new ColecaoDepartamento();
		try {
			
			listaDepartamento.setAll(dao.listarTodos(departamento));
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDepartamento.getAll();
	}
	
	@Override
	public Return validar(IModel<?> imodel) {
		return null;
	}

	@Override
	public Return validarItemUnico(IModel<?> imodel) {
		return null;
	}
	
	public Departamento getEntity(String id) {
		Departamento departamento = new Departamento();
		departamento.setIddepartamento(Integer.parseInt(id));
		ColecaoDepartamento listaDepartamento = new ColecaoDepartamento();
		try {
			listaDepartamento.setAll(dao.pesquisarID(departamento));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		departamento = ((ColecaoDepartamento) listaDepartamento).getIndice(0);
		return departamento;
	}

}
