package br.com.ueg.pids.Control;



import br.com.ueg.pids.Model.IModel;
import br.com.ueg.pids.Utils.Return;

public interface IController{


	public Return salvar(IModel<?> imodel);
	
	public Return alterar(IModel<?> imodel);
	
	public Return listar(IModel<?> imodel);
	
	public Return desativar(IModel<?> imodel);
	
//	public List<?> listarTodos(IModel<?> imodel);
}
