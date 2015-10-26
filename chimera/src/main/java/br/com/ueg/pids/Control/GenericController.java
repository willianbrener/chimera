package br.com.ueg.pids.Control;

import java.util.List;

import br.com.ueg.pids.DAO.GenericDAO;
import br.com.ueg.pids.Model.IModel;
import br.com.ueg.pids.Model.Usuario;
import br.com.ueg.pids.Utils.Return;


public abstract class GenericController<Entity> implements IController{

	protected GenericDAO dao  = new GenericDAO();
	private Entity entity;
	protected List<Entity> lstEntities;
	private List<Entity> list;
	private List<Entity> lstCriteria;    

	public void setList(List<Entity> list) {
		this.list = list;
	}

	public abstract Return validar(IModel<?> imodel);
	public abstract Return validarItemUnico(IModel<?> imodel);
	
	public Return salvar(IModel<?> imodel) {
		Return res = validarSolicitacao(imodel);
		if (res.isValid()) {
			return dao.inserir(imodel);
		}
		return res;
	}

	public Return alterar(IModel<?> imodel) {
		Return res = validarSolicitacao(imodel);
		if (res.isValid()) {
			return dao.alterar(imodel);
		}
		return res;
	}

	public Return desativar(IModel<?> imodel) {
		return  dao.excluir(imodel);
	}

	public Return ativar(IModel<?> imodel) {
		return  dao.ativar(imodel);
	}
	public Return listar(IModel<?> imodel) {
		return null;
	}
	
	
	public Return validarSolicitacao(IModel<?> imodel) {
		Return ret = new Return(true);
		ret.setValid(true);
		return ret;
	}
	public List<Entity> getLstEntities() {
		return lstEntities;
	}
	
	public List<?> getLstEntitiesAtivos(String keyword){
		return lstEntities;
	}

	public List<?> getLstEntities(String keyword) {
		return lstEntities;
	}
	
	public void setLstEntities(List<Entity> lstEntities) {
		this.lstEntities = lstEntities;
	}

	public Entity getEntity() {
		return entity;
	}
	
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public List<Entity> getLstCriteria() {
		return lstCriteria;
	}

	public void setLstCriteria(List<Entity> lstCriteria) {
		this.lstCriteria = lstCriteria;
	}

	public List<Entity> getLstEntities(String string,
			Usuario usuario) {
		return lstEntities;
	}

	public List<?> listarTodos(String keyword) {
		return lstEntities;
	}



}
