package br.com.ueg.pids.ViewModel;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.A;
import org.zkoss.zul.Div;

import br.com.ueg.pids.Control.GerenciarSolicitacoesController;
import br.com.ueg.pids.Login.AuthentificationServiceImpl2;
import br.com.ueg.pids.Model.AuthenticationService;
import br.com.ueg.pids.Model.GerenciarSolicitacoes;
import br.com.ueg.pids.Model.UserCredential;
import br.com.ueg.pids.Model.Usuario;
import br.com.ueg.pids.Utils.Return;

@SuppressWarnings("serial")
public class NavbarViewModel extends Div {
	
	@Wire
	A atask, anoti, amsg;
	
	AuthenticationService authService = new AuthentificationServiceImpl2();
	private int aprovadas, executadas, reprovadas;
	

	private int qtdSolitacoes;
	private List<GerenciarSolicitacoes> solicitacoes = new ArrayList<GerenciarSolicitacoes>();
	private Usuario usuario = new Usuario();
	Session sess = Sessions.getCurrent();
	UserCredential user = new UserCredential();
	private List<?> lstUsuarios;
	
	
	
	@Init
	public void init() {
		user = (UserCredential)sess.getAttribute("userCredential");
		populaDadosUsuario();
		populaDadosSolicitacoes();
	}
	
	public Return populaDadosSolicitacoes() {
		Return ret = new Return(true);
		GerenciarSolicitacoesController control = new GerenciarSolicitacoesController();
		setSolicitacoes(control.getLstEntities(user.getPermission(), usuario));
		if (getSolicitacoes() != null && getSolicitacoes().size() > 0) {
			for (GerenciarSolicitacoes soli : solicitacoes) {
				if(soli.getSituacao().equals("REPROVADA")){
					reprovadas++;
				}else if(soli.getSituacao().equals("APROVADA")){
					aprovadas++;
				}else if(soli.getSituacao().equals("EXECUTADA")){
					executadas++;
				}
				
			}
			
		} 
		qtdSolitacoes = reprovadas+aprovadas+executadas;
		return ret;
	}
	
	public Return populaDadosUsuario() {
		Return ret = new Return(true);
		GerenciarSolicitacoesController control = new GerenciarSolicitacoesController();
		setLstUsuarios(control.getLstUsuarioDados(user.getName()));
	
		setUsuario((Usuario) getLstUsuarios().get(0));
		
		return ret;
	}
	@Listen("onOpen = #taskpp")
	public void toggleTaskPopup(OpenEvent event) {
		toggleOpenClass(event.isOpen(), atask);
	}
	
	@Listen("onOpen = #notipp")
	public void toggleNotiPopup(OpenEvent event) {
		toggleOpenClass(event.isOpen(), anoti);
	}
	
	@Listen("onOpen = #msgpp")
	public void toggleMsgPopup(OpenEvent event) {
		toggleOpenClass(event.isOpen(), amsg);
	}
	
	@Command
	public void doLogout(){
		authService.logout();		
		Executions.sendRedirect("/login.zul");
	}

	// Toggle open class to the component
	public void toggleOpenClass(Boolean open, Component component) {
		HtmlBasedComponent comp = (HtmlBasedComponent) component;
		String scls = comp.getSclass();
		if (open) {
			comp.setSclass(scls + " open");
		} else {
			comp.setSclass(scls.replace(" open", ""));
		}
	}

	

	public int getAprovadas() {
		return aprovadas;
	}

	public void setAprovadas(int aprovadas) {
		this.aprovadas = aprovadas;
	}

	public int getExecutadas() {
		return executadas;
	}

	public void setExecutadas(int executadas) {
		this.executadas = executadas;
	}

	public int getReprovadas() {
		return reprovadas;
	}

	public void setReprovadas(int reprovadas) {
		this.reprovadas = reprovadas;
	}

	public int getQtdSolitacoes() {
		return qtdSolitacoes;
	}

	public void setQtdSolitacoes(int qtdSolitacoes) {
		this.qtdSolitacoes = qtdSolitacoes;
	}

	public List<GerenciarSolicitacoes> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(List<GerenciarSolicitacoes> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<?> getLstUsuarios() {
		return lstUsuarios;
	}

	public void setLstUsuarios(List<?> lstUsuarios) {
		this.lstUsuarios = lstUsuarios;
	}
	
}
