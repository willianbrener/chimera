package br.com.ueg.pids.Control;

import java.util.List;

import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Utils.Return;

public interface IController{

		public Return salvar(Cargo cargo);
		public Return deletar(Cargo cargo);
		public Return alterar(Cargo cargo);
		public List<Cargo> ListarTodos();
}
