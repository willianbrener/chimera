package br.com.ueg.pids.ViewModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Messagebox;

import br.com.ueg.pids.Control.DepartamentoController;
import br.com.ueg.pids.Control.GerenciarSolicitacoesController;
import br.com.ueg.pids.Control.RecursoController;
import br.com.ueg.pids.Converter.DateUtils;
import br.com.ueg.pids.Enum.Permissao;
import br.com.ueg.pids.Enum.SituacaoSolicitacao;
import br.com.ueg.pids.Enum.TypeMessage;
import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Model.Departamento;
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
	private List<GerenciarSolicitacoes> lstFiltroDepartamento = new ArrayList<GerenciarSolicitacoes>();
	private List<GerenciarSolicitacoes> lstFiltroSituacao = new ArrayList<GerenciarSolicitacoes>();
	private List<GerenciarSolicitacoes> lstFiltroRecurso = new ArrayList<GerenciarSolicitacoes>();
	private List<Permissao> permissaoList = new ArrayList<Permissao>();
	private List<?> lstSolicitacao;
	private Usuario usuario = new Usuario();
	private List<?> lstUsuarios;
	private List<Recurso> lstRecurso,
			lstRecursoFiltro = new ArrayList<Recurso>();
	private List<Departamento> lstDepartamentoFiltro = new ArrayList<Departamento>();
	private List<SituacaoSolicitacao> lstSituacaoFiltro = new ArrayList<SituacaoSolicitacao>();
	private Date data = new Date();
	private Integer solicitacaoSelectedIndex;
	private DateUtils dateUtils = new DateUtils();
	private boolean filtroStatus, filtroDepart, filtroRec;
	private Recurso recFilter = new Recurso();
	private Departamento depFilter = new Departamento();
	private SituacaoSolicitacao situFilter;
	private Utils utils = new Utils();

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
					"Solicitação realizada com sucesso!");
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
			msgbox.mensagem(TypeMessage.AVISO, "Solicitaçãoo já aprovada!");
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
	public Return telaDetalhar() {
		Return ret = new Return(true);

		if (itemSelected == null) {
			msgbox.mensagem(TypeMessage.AVISO,
					"Selecione algum item para alterar!");
		} else {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("SolicitacaoObject", this.itemSelected);
			map.put("recordMode", "EDIT");
			setSolicitacaoSelectedIndex(lstSolicitacoes.indexOf(itemSelected));
			Executions.createComponents(
					"/paginas/gerenciar_solicitacoes/solicitacao_detalhar.zul",
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
											"Solicitação deletada com sucesso!");
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@NotifyChange("lstSolicitacoes")
	@Command
	public Return aprovar() {
		Return ret = new Return(false);
		if (getItemSelected() != null
				&& getItemSelected().getSituacao().equals("PENDENTE")) {
			Messagebox.show("Deseja aprovar solicitação?", "Confirm",
					Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
					new EventListener() {
						public void onEvent(Event event) throws Exception {
							if (event.getName().equals("onNo")) {
								return;
							} else {
								if (event.getName().equals("onYes")) {
									getControl().alterarSolicitacao(
											getItemSelected(), "APROVADA");
								}
							}
						}
					});
		} else if (getItemSelected() == null) {
			msgbox.mensagem(TypeMessage.AVISO, "Selecione uma solicitação!");
		} else if (getItemSelected().getSituacao().equals("APROVADA")) {
			msgbox.mensagem(TypeMessage.AVISO, "Solicitação já aprovada!");
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
		Return ret = new Return(true);
		if (getItemSelected() != null
				&& getItemSelected().getSituacao().equals("PENDENTE")) {
			ret = telaRecusar();

			if (ret.isValid()) {
				ret = getControl().alterarSolicitacao(getItemSelected(),
						"REPROVADA");
			}
		} else if (getItemSelected() == null) {
			msgbox.mensagem(TypeMessage.AVISO, "Selecione uma solicitação!");
		}
		if (ret.isValid()) {
			limpar();
			getSolicitacoesList(getUsuario().getPermissao(), getUsuario());
		}
		return ret;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@NotifyChange("lstSolicitacoes")
	@Command
	public Return executar() {
		Return ret = new Return(false);
		if (getItemSelected() != null
				&& getItemSelected().getSituacao().equals("APROVADA")) {
			Messagebox.show("Deseja executar solicitação?", "Confirm",
					Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
					new EventListener() {
						public void onEvent(Event event) throws Exception {
							if (event.getName().equals("onNo")) {
								return;
							} else {
								if (event.getName().equals("onYes")) {
									getControl().alterarSolicitacao(
											getItemSelected(), "EXECUTADA");
								}
							}
						}
					});
		} else if (getItemSelected() == null) {
			msgbox.mensagem(TypeMessage.AVISO, "Selecione uma solicitação!");
		}else if(getItemSelected() != null
				&& getItemSelected().getSituacao().equals("EXECUTADA")){
			msgbox.mensagem(TypeMessage.AVISO, "Solicitação já executada!");
		}
		if (ret.isValid()) {
			limpar();
			getSolicitacoesList(getUsuario().getPermissao(), getUsuario());
		}
		return ret;
	}

	@NotifyChange("lstSolicitacoes")
	@Command
	public Return checkFiltro(@ContextParam(ContextType.VIEW) Component view) {
		Return ret = new Return(true);
		Selectors.wireComponents(view, this, false);

		if (filtroStatus && lstFiltroSituacao.size() == 0
				&& getSituFilter() != null) {
			List<Integer> vetor = new ArrayList<Integer>();
			if (lstSolicitacoes != null) {
				for (int i = 0; i < lstSolicitacoes.size(); i++) {

					if (!lstSolicitacoes.get(i).getSituacao()
							.equals(getSituFilter().getNome())) {
						lstFiltroSituacao.add(lstSolicitacoes.get(i));
						vetor.add(i);
					}
				}
				for (int j = vetor.size() - 1; j >= 0; j--) {

					lstSolicitacoes.remove(vetor.get(j).intValue());
					vetor.remove(j);
				}
			} else {
				msgbox.mensagem(TypeMessage.AVISO, "Lista Vazia!");
			}
		} else if (!filtroStatus && lstFiltroSituacao.size() > 0) {
			lstSolicitacoes.addAll(lstFiltroSituacao);
			lstFiltroSituacao.clear();
		}

		if (filtroDepart && lstFiltroDepartamento.size() == 0
				&& getDepFilter() != null) {
			List<Integer> vetor = new ArrayList<Integer>();
			if (lstSolicitacoes != null) {
				for (int i = 0; i < lstSolicitacoes.size(); i++) {

					if (!(lstSolicitacoes.get(i).getUsuario().getCargo()
							.getDepartamento().getIddepartamento() == getDepFilter()
							.getIddepartamento())) {
						lstFiltroDepartamento.add(lstSolicitacoes.get(i));
						vetor.add(i);
					}
				}
				for (int j = vetor.size() - 1; j >= 0; j--) {

					lstSolicitacoes.remove(vetor.get(j).intValue());
					vetor.remove(j);
				}
			} else {
				msgbox.mensagem(TypeMessage.AVISO, "Lista Vazia!");
			}
		} else if (!filtroDepart && lstFiltroDepartamento.size() > 0) {
			lstSolicitacoes.addAll(lstFiltroDepartamento);
			lstFiltroDepartamento.clear();
		}

		if (filtroRec && lstFiltroRecurso.size() == 0 && getRecFilter() != null) {
			List<Integer> vetor = new ArrayList<Integer>();
			if (lstSolicitacoes != null) {
				for (int i = 0; i < lstSolicitacoes.size(); i++) {

					if (!(lstSolicitacoes.get(i).getRecurso().getIdrecurso() == getRecFilter()
							.getIdrecurso())) {
						lstFiltroRecurso.add(lstSolicitacoes.get(i));
						vetor.add(i);
					}
				}
				for (int j = vetor.size() - 1; j >= 0; j--) {

					lstSolicitacoes.remove(vetor.get(j).intValue());
					vetor.remove(j);
				}
			} else {
				msgbox.mensagem(TypeMessage.AVISO, "Lista Vazia!");
			}
		} else if (!filtroRec && lstFiltroRecurso.size() > 0) {
			lstSolicitacoes.addAll(lstFiltroRecurso);
			lstFiltroRecurso.clear();
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

	@NotifyChange("lstRecursoFiltro")
	public List<Recurso> getRecursoFiltro() {
		RecursoController recursoController = new RecursoController();
		lstRecurso = recursoController.getLstEntities();
		return lstRecurso;
	}

	@NotifyChange("lstDepartamentoFiltro")
	public List<Departamento> getDepartamentoFiltro() {
		DepartamentoController departamentoController = new DepartamentoController();
		lstDepartamentoFiltro = departamentoController.getLstEntities();
		return lstDepartamentoFiltro;
	}

	@NotifyChange("lstSituacaoFiltro")
	public List<SituacaoSolicitacao> getSituacaoFiltro() {
		for (SituacaoSolicitacao situacao : SituacaoSolicitacao.values()) {
			lstSituacaoFiltro.add(situacao);
		}

		return lstSituacaoFiltro;
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

	public DateUtils getDateUtils() {
		return dateUtils;
	}

	public void setDateUtils(DateUtils dateUtils) {
		this.dateUtils = dateUtils;
	}

	public boolean isFiltroStatus() {
		return filtroStatus;
	}

	public void setFiltroStatus(boolean filtroStatus) {
		this.filtroStatus = filtroStatus;
	}

	public boolean isFiltroDepart() {
		return filtroDepart;
	}

	public void setFiltroDepart(boolean filtroDepart) {
		this.filtroDepart = filtroDepart;
	}

	public boolean isFiltroRec() {
		return filtroRec;
	}

	public void setFiltroRec(boolean filtroRec) {
		this.filtroRec = filtroRec;
	}

	public List<Recurso> getLstRecursoFiltro() {
		return lstRecursoFiltro;
	}

	public void setLstRecursoFiltro(List<Recurso> lstRecursoFiltro) {
		this.lstRecursoFiltro = lstRecursoFiltro;
	}

	public List<Departamento> getLstDepartamentoFiltro() {
		return lstDepartamentoFiltro;
	}

	public void setLstDepartamentoFiltro(
			List<Departamento> lstDepartamentoFiltro) {
		this.lstDepartamentoFiltro = lstDepartamentoFiltro;
	}

	public List<SituacaoSolicitacao> getLstSituacaoFiltro() {
		return lstSituacaoFiltro;
	}

	public void setLstSituacaoFiltro(List<SituacaoSolicitacao> lstSituacaoFiltro) {
		this.lstSituacaoFiltro = lstSituacaoFiltro;
	}

	public Recurso getRecFilter() {
		return recFilter;
	}

	public void setRecFilter(Recurso recFilter) {
		this.recFilter = recFilter;
	}

	public Departamento getDepFilter() {
		return depFilter;
	}

	public void setDepFilter(Departamento depFilter) {
		this.depFilter = depFilter;
	}

	public SituacaoSolicitacao getSituFilter() {
		return situFilter;
	}

	public void setSituFilter(SituacaoSolicitacao situFilter) {
		this.situFilter = situFilter;
	}

	public List<GerenciarSolicitacoes> getLstFiltroSituacao() {
		return lstFiltroSituacao;
	}

	public void setLstFiltroSituacao(
			List<GerenciarSolicitacoes> lstFiltroSituacao) {
		this.lstFiltroSituacao = lstFiltroSituacao;
	}

	public List<GerenciarSolicitacoes> getLstFiltroRecurso() {
		return lstFiltroRecurso;
	}

	public void setLstFiltroRecurso(List<GerenciarSolicitacoes> lstFiltroRecurso) {
		this.lstFiltroRecurso = lstFiltroRecurso;
	}

	public List<GerenciarSolicitacoes> getLstFiltroDepartamento() {
		return lstFiltroDepartamento;
	}

	public void setLstFiltroDepartamento(
			List<GerenciarSolicitacoes> lstFiltroDepartamento) {
		this.lstFiltroDepartamento = lstFiltroDepartamento;
	}

}
