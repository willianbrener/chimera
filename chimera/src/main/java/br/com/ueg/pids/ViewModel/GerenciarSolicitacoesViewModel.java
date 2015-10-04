package br.com.ueg.pids.ViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Messagebox;

import br.com.ueg.pids.Control.GerenciarSolicitacoesController;
import br.com.ueg.pids.Control.RecursoController;
import br.com.ueg.pids.Converter.DateUtils;
import br.com.ueg.pids.Enum.Permissao;
import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Model.GerenciarSolicitacoes;
import br.com.ueg.pids.Model.Recurso;
import br.com.ueg.pids.Model.UserCredential;
import br.com.ueg.pids.Model.Usuario;
import br.com.ueg.pids.Utils.Return;
import br.com.ueg.pids.Utils.Utils;

@SuppressWarnings("serial")
public class GerenciarSolicitacoesViewModel
		extends
		GenericViewModel<GerenciarSolicitacoes, GerenciarSolicitacoesController> {

	private Cargo cargoSelected = new Cargo();
	private Permissao permissaoSelecionada;
	private List<GerenciarSolicitacoes> lstSolicitacoes = new ArrayList<GerenciarSolicitacoes>();
	private List<Permissao> permissaoList = new ArrayList<Permissao>();

	private Usuario usuario = new Usuario();
	private List<?> lstUsuarios;
	private List<Recurso> lstRecurso;
	private Date data = new Date();

	@Init
	public void init() {
		super.init();
		usuario.setFullName(user.getName());
		if (getEntity() != null && verificaComponent().equals("criar")) {
			populaDadosUsuario();
		} else if (verificaComponent().equals("listar")) {
			setLstUsuarios(getControl().getLstUsuarioDados(usuario.getFullName()));
			setUsuario((Usuario) getLstUsuarios().get(0));
		}

		if ( (lstSolicitacoes == null || lstSolicitacoes.size() == 0) && verificaComponent().equals("listar")) {
			getSolicitacoesList(getUsuario().getPermissao(),getUsuario());
		} 
	}

	@Command
	@NotifyChange("entity")
	public Return setaValores() {
		Return ret = new Return(true);

		if (getItemSelected() != null) {
			setEntity(getItemSelected());
		}

		return ret;
	}

	public Return populaDadosUsuario() {
		Return ret = new Return(true);
		setLstUsuarios(getControl().getLstUsuarioDados(usuario.getFullName()));
		if (getLstUsuarios() != null && getLstUsuarios().size() == 1) {
			getEntity().setUsuario((Usuario) getLstUsuarios().get(0));
		} else {

		}
		return ret;
	}

	public String verificaComponent() {
		Utils utils = new Utils();
		String ret = null;
		if (utils.verificaPaginaSolicitacoes(
				Executions.getCurrent().getDesktop().getRequestPath()).equals(
				"listar")) {
			ret = "listar";
		} else if (utils.verificaPaginaSolicitacoes(
				Executions.getCurrent().getDesktop().getRequestPath()).equals(
				"criar")) {
			ret = "criar";
		}
		return ret;
	}

	@Command
	public Return salvar() {
		Return ret = new Return(true);
		DateUtils dateUtils = new DateUtils();

		String newDate = dateUtils.DateToString(getData());
		getEntity().setAtivo(true);
		getEntity().setData(newDate);
		getEntity().setSituacao("PENDENTE");
		ret = getControl().salvar(getEntity());
		if (ret.isValid()) {
			Messagebox.show("Solicitação realizada com sucesso!", "Sucess",
					Messagebox.OK, Messagebox.INFORMATION);
			Executions.sendRedirect("/paginas/gerenciar_solicitacoes/approver/pesquisar.zul");
		}

		return null;
	}
	@NotifyChange("entity")
	@Command
	public Return limpar(){
		Return ret = new Return(true);
		if(getEntity() != null){
			setEntity(null);
			setItemSelected(null);
		}
		return ret;
	}
	
	@NotifyChange("lstSolicitacoes")
	@Command
	public Return aprovar(){
		Return ret = new Return(false);
		if(getItemSelected()!= null && getItemSelected().getSituacao().equals("PENDENTE")){
			ret = getControl().alterarSolicitacao(getItemSelected(),"APROVADA");
		}else if(getItemSelected()== null){
			Messagebox.show("Selecione uma solicitação!", "AVISO",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}else if(getItemSelected().getSituacao().equals("APROVADA")){
			Messagebox.show("Solicitação já aprovada!", "AVISO",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
		if(ret.isValid()){
			limpar();
			getSolicitacoesList(getUsuario().getPermissao(),getUsuario());
		}
		
		return ret;
	}
	
	@NotifyChange("entity")
	@Command
	public Return rejeitar(){
		Return ret = new Return(false);
		if(getItemSelected()!= null && getItemSelected().getSituacao().equals("PENDENTE")){
			ret = getControl().alterarSolicitacao(getItemSelected(),"APROVADA");
		}else if(getItemSelected()== null){
			Messagebox.show("Selecione uma solicitação!", "AVISO",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}else if(getItemSelected().getSituacao().equals("APROVADA")){
			Messagebox.show("Solicitação já aprovada!", "AVISO",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
		if(ret.isValid()){
			limpar();
			getSolicitacoesList(getUsuario().getPermissao(),getUsuario());
		}
		return ret;
	}
	
	
	@Override
	public GerenciarSolicitacoes getObject() {
		return new GerenciarSolicitacoes();
	}

	@Override
	public GerenciarSolicitacoesController getControl() {
		return new GerenciarSolicitacoesController();
	}

	@NotifyChange("permissaoList")
	public List<Permissao> getPermissaoList() {
		for (Permissao permissao : Permissao.values()) {
			permissaoList.add(permissao);
		}
		return permissaoList;
	}

	@NotifyChange("lstRecurso")
	public List<Recurso> getRecursoList() {
		RecursoController recursoController = new RecursoController();
		lstRecurso = recursoController.getLstEntities();
		return lstRecurso;
	}

	@NotifyChange("lstSolicitacoes")
	public List<GerenciarSolicitacoes> getSolicitacoesList(String string, Usuario usuario) {
		GerenciarSolicitacoesController solicitacoesController = new GerenciarSolicitacoesController();
		lstSolicitacoes = solicitacoesController.getLstEntities(string,usuario);
		return lstSolicitacoes;
	}

	public Permissao getPermissaoSelecionada() {
		return permissaoSelecionada;
	}

	public void setPermissaoSelecionada(Permissao permissaoSelecionada) {
		this.permissaoSelecionada = permissaoSelecionada;
	}

	public void setPermissaoList(List<Permissao> permissaoList) {
		this.permissaoList = permissaoList;
	}

	public List<Recurso> getLstRecurso() {
		return lstRecurso;
	}

	public void setLstRecurso(List<Recurso> lstRecurso) {
		this.lstRecurso = lstRecurso;
	}

	public Cargo getCargoSelected() {
		return cargoSelected;
	}

	public void setCargoSelected(Cargo cargoSelected) {
		this.cargoSelected = cargoSelected;
	}

	public UserCredential getUser() {
		return user;
	}

	public void setUser(UserCredential user) {
		this.user = user;
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

	public List<GerenciarSolicitacoes> getLstSolicitacoes() {
		return lstSolicitacoes;
	}

	public void setLstSolicitacoes(List<GerenciarSolicitacoes> lstSolicitacoes) {
		this.lstSolicitacoes = lstSolicitacoes;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
