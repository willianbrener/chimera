package br.com.ueg.pids.ViewModel;



import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import org.springframework.util.StringUtils;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;

import br.com.ueg.pids.Control.GenericController;
import br.com.ueg.pids.Model.GenericModel;
import br.com.ueg.pids.Model.UserCredential;
import br.com.ueg.pids.Utils.Connect;
import br.com.ueg.pids.Utils.GenericNotification;
import br.com.ueg.pids.Utils.Message;
import br.com.ueg.pids.Utils.Return;

@SuppressWarnings({ "deprecation", "serial", "rawtypes" })
public abstract class GenericViewModel<M extends GenericModel, C extends GenericController<M>> extends GenericForwardComposer{
	protected Component component;
	private M entity;
	protected C controller;
	protected AnnotateDataBinder binder;
	protected M itemSelected;
	Session sess = Sessions.getCurrent();
	UserCredential user = new UserCredential();
	protected String keyword;
	GenericNotification msgbox = new GenericNotification();

	public abstract M getObject();

	public abstract C getControl();

	@Init
	public void init() {
		user = (UserCredential)sess.getAttribute("userCredential");
		setEntity(getObject());
		entity = getEntity();
	}

	
	
	public void gerarRelatorio(String nomeArquivoJrxml, Map<String, Object> param) throws JRException, SQLException{
		try {

			JasperReport jr = JasperCompileManager.compileReport(Executions
					.getCurrent().getSession().getWebApp()
					.getRealPath("/relatorios/" + nomeArquivoJrxml + ".jrxml"));
			
			JasperPrint jp = JasperFillManager.fillReport(jr, param,Connect.connectionSimple());
			JasperViewer jasperViewer = new JasperViewer(jp, false);
			jasperViewer.setTitle("Relatório");  
			jasperViewer.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			jasperViewer.setVisible(true);
			Connect.close();
		} catch (JRException e) {
			e.printStackTrace();
		}

	}
	public Component getComponentById(Component comp, String id) {
		if (comp.getId().equals(id)) {
			return comp;
		}
		List<Component> allComps = comp.getChildren();
		for (Component c : allComps) {
			Component aux = getComponentById(c, id);
			if (aux != null) {
				return aux;
			}
		}
		return null;
	}
	
	@Deprecated
	public void showNotifications(List<Message> messages, String type) {
		String geralMessage = "";
		for (Message message : messages) {
			if (message.getMessage() == null || message.getMessage().isEmpty())
				continue;
			if (message.getAssociated() == null
					|| message.getAssociated().equals("")) {
				geralMessage += message.getMessage() + "<br><br>";
			} else {
				Clients.showNotification(
						message.getMessage(),
						type,
						getComponentById(component,	"fld" + StringUtils.capitalize(message.getAssociated())),
						"end_center", 0, false);
			}
		}
		if (!geralMessage.equals("")) {
			Clients.showNotification(geralMessage, type, null, "middle_center",
					0, false);
		}
	}
	
	public void treatReturn(Return ret) {
		if (ret != null && ret.getMessages() != null && !ret.getMessages().isEmpty()) {
			String type = ret.isValid() ? "info" : "error";
			showNotifications(ret.getMessages(), type);
		}
	}
	
	public Component getComponentById(String id) {
		return getComponentById(component, id);
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


	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}
