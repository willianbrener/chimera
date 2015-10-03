package br.com.ueg.pids.ViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Messagebox;

import br.com.ueg.pids.Control.DepartamentoController;
import br.com.ueg.pids.Control.GerenciarSolicitacoesController;
import br.com.ueg.pids.Control.RecursoController;
import br.com.ueg.pids.Converter.DateUtils;
import br.com.ueg.pids.Enum.Permissao;
import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Model.Departamento;
import br.com.ueg.pids.Model.GerenciarSolicitacoes;
import br.com.ueg.pids.Model.Recurso;
import br.com.ueg.pids.Model.UserCredential;
import br.com.ueg.pids.Model.Usuario;
import br.com.ueg.pids.Utils.Return;


@SuppressWarnings("serial")
public class GerenciarSolicitacoesViewModel extends GenericViewModel<GerenciarSolicitacoes, GerenciarSolicitacoesController>{
/*
* USUARIO = READONLY
* CARGO = DROPDOWN
* AO TRAZER CARGO TRAZER DEPARTAMENTO REFERENTE
* RECURSOS = DROPDOWN
* 
*/
	private Cargo cargoSelected = new Cargo();
	private Permissao permissaoSelecionada;
	private List<GerenciarSolicitacoes> lstSolicitacoes = new ArrayList<GerenciarSolicitacoes>();
	private List<Permissao> permissaoList = new ArrayList<Permissao>();
	Session sess = Sessions.getCurrent();
	UserCredential user = new UserCredential();
	private Usuario usuario = new Usuario();
	private List<?> lstUsuarios;
	private List<Recurso> lstRecurso;
	private int aux = 0;
	private Date data = new Date();
	
	@Init
	public void init() {		
		user = (UserCredential)sess.getAttribute("userCredential");
		super.init();

		if(getEntity()!= null && aux == 0){
			usuario.setFullName(user.getName());
			aux = 1;
			populaDadosUsuario();
		}else{
			System.out.println("Entidade nula.");
		}
		
		if(lstSolicitacoes == null || lstSolicitacoes.size()== 0){
			getSolicitacoesList();
		}
		
			
		
	}
	
	public Return populaDadosUsuario(){
		Return ret = new Return(true);
		setLstUsuarios(getControl().getLstUsuarioDados(usuario.getFullName()));
		if(getLstUsuarios() != null && getLstUsuarios().size() == 1){
			getEntity().setUsuario((Usuario) getLstUsuarios().get(0));
			getEntity().getUsuario().setFullName(usuario.getFullName());
		}else{
			
		}
		return ret;
	}
	
	@Command
	public Return salvar() {
		Return ret = new Return(true);
		DateUtils dateUtils = new DateUtils();
		
		String newDate = dateUtils.DateToString(getData());
					getEntity().setAtivo(true);
					getEntity().setPermissao(permissaoSelecionada.getNome());
					getEntity().setData(newDate);
					ret = getControl().salvar(getEntity());
			if (ret.isValid()) {
				Messagebox.show("Solicitação realizada com sucesso!","Sucess",
						Messagebox.OK, Messagebox.INFORMATION);
				Executions
						.sendRedirect("/paginas/gerenciar_solicitacoes/pesquisar.zul");
		
		}

		return null;
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
	public List<GerenciarSolicitacoes> getSolicitacoesList() {
		GerenciarSolicitacoesController solicitacoesController = new GerenciarSolicitacoesController();
		lstSolicitacoes = solicitacoesController.getLstEntities();
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
