package br.com.ueg.pids.ViewModel;



import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Session;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;

import br.com.ueg.pids.Control.GenericController;
import br.com.ueg.pids.Model.GenericModel;

@SuppressWarnings({ "deprecation", "serial" })
public abstract class GenericViewModel<M extends GenericModel, C extends GenericController<M>> extends Div{

	private M entity;
	protected C controller;
	protected AnnotateDataBinder binder;
	protected M itemSelected;
	Session session;
	protected String keyword;
	

	public abstract M getObject();

	public abstract C getControl();

	@Init
	public void init() {

		setEntity(getObject());
		entity = getEntity();
	}

	public void loadBinder() {
		binder.loadAll();
	}

	public M getEntity() {
		return entity;
	}

	public void setEntity(M entity) {
		this.entity = entity;
	}

	public C getController() {
		return controller;
	}

	public void setController(C controller) {
		this.controller = controller;
	}

	public M getItemSelected() {
		return itemSelected;
	}

	public void setItemSelected(M itemSelected) {
		this.itemSelected = itemSelected;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}
