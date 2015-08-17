package br.com.ueg.pids.Control;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zul.Messagebox;

import br.com.ueg.pids.DAO.CargoDAO;
import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Utils.Connect;
import br.com.ueg.pids.Utils.Return;

public abstract class GenericController <Entity> implements IController{

	private CargoDAO dao;

	public abstract Return validar(Cargo cargo);
	
	public Return alterar(Cargo cargo){
		Return ret = validar(cargo);
		try {
			dao = new CargoDAO(Connect.fabricar());
			dao.update(cargo);
		} catch (Exception e1) {
			e1.printStackTrace();
			Messagebox.show("Erro conexão com o banco!!", "Error",
					Messagebox.OK, Messagebox.ERROR);
		}
		return ret;
	}
	
	public Return salvar(Cargo cargo) {
		Return ret = validar(cargo);
		try {
			dao = new CargoDAO(Connect.fabricar());
			dao.cadastrar(cargo);
		} catch (Exception e1) {
			e1.printStackTrace();
			Messagebox.show("Erro conexão com o banco!!", "Error",
					Messagebox.OK, Messagebox.ERROR);
		}
	
	return ret;
	}

	public List<Cargo> ListarTodos() {
		List<Cargo> cargos = new ArrayList<Cargo>();
		try {

			dao = new CargoDAO(Connect.fabricar());
			cargos = dao.listarTodos();
		} catch (Exception e1) {
			
			e1.printStackTrace();
			Messagebox.show("Erro conexão com o banco!!", "Error",
					Messagebox.OK, Messagebox.ERROR);
		}
		return cargos;
	}

	public Return deletar(Cargo cargo) {
		Return ret = new Return(true);
		try {
			dao = new CargoDAO(Connect.fabricar());
			dao.deletar(cargo);
		} catch (Exception e1) {
			e1.printStackTrace();
			Messagebox.show("Erro conexão com o banco!!", "Error",
					Messagebox.OK, Messagebox.ERROR);
		}
		return ret;
	}
	
	public List<Cargo> pesquisa(String busca){
		List<Cargo> cargos = new ArrayList<Cargo>();
		try {
			dao = new CargoDAO(Connect.fabricar());
			cargos = dao.Pesquisa(busca);
		} catch (Exception e1) {
			e1.printStackTrace();
			Messagebox.show("Erro conexão com o banco!!", "Error",
					Messagebox.OK, Messagebox.ERROR);
		}
		return cargos;
	}
}
