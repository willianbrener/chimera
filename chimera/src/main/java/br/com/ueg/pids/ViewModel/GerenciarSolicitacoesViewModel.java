package br.com.ueg.pids.ViewModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;

import br.com.ueg.pids.Control.GerenciarSolicitacoesController;
import br.com.ueg.pids.Control.RecursoController;
import br.com.ueg.pids.Converter.DateUtils;
import br.com.ueg.pids.Enum.Permissao;
import br.com.ueg.pids.Enum.TypeMessage;
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
	private List<?> lstSolicitacao;
	private Usuario usuario = new Usuario();
	private List<?> lstUsuarios;
	private List<Recurso> lstRecurso;
	private Date data = new Date();
	private Integer solicitacaoSelectedIndex;
	private DateUtils dateUtils = new DateUtils();

	@Init
	public void init() {
		super.init();
		usuario.setNome(user.getName());
		if (getEntity() != null && verificaComponent().equals("criar")) {
			populaDadosUsuario();
			getEntity().setHora(dateUtils.HourToString(new Date()));
		} else if (verificaComponent().equals("listar")) {
			setLstUsuarios(getControl().getLstUsuarioDados(usuario.getNome()));
			setUsuario((Usuario) getLstUsuarios().get(0));
		}

		if ((lstSolicitacoes == null || lstSolicitacoes.size() == 0)
				&& verificaComponent().equals("listar")) {
			getSolicitacoesList(getUsuario().getPermissao(), getUsuario());
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
		setLstUsuarios(getControl().getLstUsuarioDados(usuario.getNome()));
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
	public Return salvar() throws SQLException {
		Return ret = new Return(true);

		String newDate = dateUtils.DateToString(getData());
		getEntity().setAtivo(true);
		getEntity().setData(newDate);
		getEntity().setSituacao("PENDENTE");
		ret = getControl().salvar(getEntity());
		if (ret.isValid()) {
			msgbox.mensagem(TypeMessage.SUCESSO,
					"Solicita��o realizada com sucesso!");
			if (getEntity().getUsuario().getPermissao().equals("TOTAL")) {
				Executions
						.sendRedirect("/paginas/gerenciar_solicitacoes/administrator/pesquisar.zul");
			} else if (getEntity().getUsuario().getPermissao()
					.equals("APROVADOR")) {
				Executions
						.sendRedirect("/paginas/gerenciar_solicitacoes/approver/pesquisar.zul");
			} else if (getEntity().getUsuario().getPermissao()
					.equals("SOLICITANTE")) {
				Executions
						.sendRedirect("/paginas/gerenciar_solicitacoes/user/pesquisar.zul");
			} else if (getEntity().getUsuario().getPermissao()
					.equals("EXECUTOR")) {
				Executions
						.sendRedirect("/paginas/gerenciar_solicitacoes/executioner/pesquisar.zul");
			}

		} else {

		}

		return null;
	}

	@Command
	public Return telaAlterar() {
		Return ret = new Return(true);
		if (itemSelected == null) {
			msgbox.mensagem(TypeMessage.AVISO,
					"Selecione algum item para alterar!");
		} else if (itemSelected.getSituacao().equalsIgnoreCase("APROVADA")) {
			msgbox.mensagem(TypeMessage.AVISO, "Solicitação já aprovada!");
		} else if (!itemSelected.getSituacao().equalsIgnoreCase("APROVADA")
				&& itemSelected != null) {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("SolicitacaoObject", this.itemSelected);
			map.put("recordMode", "EDIT");
			setSolicitacaoSelectedIndex(lstSolicitacoes.indexOf(itemSelected));
			Executions
					.createComponents(
							"/paginas/gerenciar_solicitacoes/solicitacao_component.zul",
							null, map);
		}
		return ret;
	}

	@Command
	public Return telaRecusar() {
		Return ret = new Return(true);
		if (itemSelected != null) {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("SolicitacaoObject", this.itemSelected);
			map.put("recordMode", "EDIT");
			setSolicitacaoSelectedIndex(lstSolicitacoes.indexOf(itemSelected));
			Executions.createComponents(
					"/paginas/gerenciar_solicitacoes/motivo_component.zul",
					null, map);
			// setItemSelected(null);
		} else {
			msgbox.mensagem(TypeMessage.AVISO,
					"Selecione algum item para recusar!");
		}
		return ret;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange("lstSolicitacoes")
	public Return deletar() {

		Return ret = new Return(true);
		if (itemSelected == null) {
			msgbox.mensagem(TypeMessage.ERROR,
					"Selecione um item para ser deletado!");
		} else {
			String str = "Deseja deletar a solicitacao \""
					+ getItemSelected().getTitulo() + "\"?";
			Messagebox.show(str, "Confirm", Messagebox.YES | Messagebox.NO,
					Messagebox.QUESTION, new EventListener() {

						public void onEvent(Event event) throws Exception {
							if (event.getName().equals("onNo")) {
								return;
							} else {
								if (event.getName().equals("onYes")) {

									getControl().desativar(getItemSelected());
									msgbox.mensagem(TypeMessage.SUCESSO,
											"Solicita��o deletada com sucesso!");
									setItemSelected(null);
								}
							}
						}
					});

		}
		return ret;
	}

	@NotifyChange("entity")
	@Command
	public Return limpar() {
		Return ret = new Return(true);
		if (getEntity() != null) {
			setEntity(null);
			setItemSelected(null);
		}
		return ret;
	}

	@NotifyChange("lstSolicitacoes")
	@Command
	public Return aprovar() {
		Return ret = new Return(false);
		if (getItemSelected() != null
				&& getItemSelected().getSituacao().equals("PENDENTE")) {
			ret = getControl()
					.alterarSolicitacao(getItemSelected(), "APROVADA");
			msgbox.mensagem(TypeMessage.SUCESSO, "Solicitacao Aprovada!");
		} else if (getItemSelected() == null) {
			msgbox.mensagem(TypeMessage.AVISO, "Selecione uma solicita��o!");
		} else if (getItemSelected().getSituacao().equals("APROVADA")) {
			msgbox.mensagem(TypeMessage.AVISO, "Solicita��o j� aprovada!");
		}
		if (ret.isValid()) {
			limpar();
			getSolicitacoesList(getUsuario().getPermissao(), getUsuario());
		}

		return ret;
	}

	@NotifyChange("lstSolicitacoes")
	@Command
	public Return rejeitar() {
		Return ret = new Return(false);
		if (getItemSelected() != null
				&& getItemSelected().getSituacao().equals("PENDENTE")) {
			ret = getControl().alterarSolicitacao(getItemSelected(),
					"REPROVADA");
			if (ret.isValid()) {
				ret = telaRecusar();
			}
		} else if (getItemSelected() == null) {
			msgbox.mensagem(TypeMessage.AVISO, "Selecione uma solicita��o!");
		}
		if (ret.isValid()) {
			limpar();
			getSolicitacoesList(getUsuario().getPermissao(), getUsuario());
		}
		return ret;
	}

	@NotifyChange("lstSolicitacoes")
	@Command
	public Return executar() {
		Return ret = new Return(false);
		if (getItemSelected() != null
				&& getItemSelected().getSituacao().equals("APROVADA")) {
			ret = getControl().alterarSolicitacao(getItemSelected(),
					"EXECUTADA");
		} else if (getItemSelected() == null) {
			msgbox.mensagem(TypeMessage.AVISO, "Selecione uma solicita��o!");
		}
		if (ret.isValid()) {
			limpar();
			getSolicitacoesList(getUsuario().getPermissao(), getUsuario());
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
	public List<GerenciarSolicitacoes> getSolicitacoesList(String string,
			Usuario usuario) {
		GerenciarSolicitacoesController solicitacoesController = new GerenciarSolicitacoesController();
		lstSolicitacoes = solicitacoesController
				.getLstEntities(string, usuario);
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

	public List<?> getLstSolicitacao() {
		return lstSolicitacao;
	}

	public void setLstSolicitacao(List<?> lstSolicitacao) {
		this.lstSolicitacao = lstSolicitacao;
	}

	public Integer getSolicitacaoSelectedIndex() {
		return solicitacaoSelectedIndex;
	}

	public void setSolicitacaoSelectedIndex(Integer solicitacaoSelectedIndex) {
		this.solicitacaoSelectedIndex = solicitacaoSelectedIndex;
	}

}
